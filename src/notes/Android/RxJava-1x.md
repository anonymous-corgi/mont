# RxJava

- ###

  + #####
    +

- ### Reference Websites:
  [给Android开发者的RxJava详解](https://gank.io/post/560e15be2dca930e00da1083)
  
- ### 1. Basic class and interface

  + ##### 1.1 functions: ***Action0*** & ***Action1\<T\>*** & ...& ***ActionN***

  ```java
  public interface Action0 extends Action {
      void call();
  }

  public interface Action1<T> extends Action {
      void call(T t);
  }

  public interface ActionN extends Action {
      void call(Object... args);
  }
  ```
  
  + ##### 1.2 rx: ***Observer\<T\>*** & ***Subscription*** & ***Subscriber\<T\>***
  ```java
  public interface Observer<T> {
    
      void onCompleted();

      void onError(Throwable e);

      void onNext(T t);
  }
  ```  
  
  ```java
  public interface Subscription {
    
      void unsubscribe();

      boolean isUnsubscribed();
  }
  ```
  
  ```java
  public abstract class Subscriber<T> implements Observer<T>, Subscription {
    
      public void onStart() {
          // do nothing by default
      }
  }
  ```
  
  + ##### 1.3 rx: ***Observer.OnSubscribe\<T\>***
  ```java
  public class Observable<T> {
    
      final OnSubscribe<T> onSubscribe;

      public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
          // cover for generics insanity
      }
  }
  ```

- ### 2. Observer<T>.subscribe()
  All These non-static subscribe() methods below will be converted to
  one final execution method.  
  **The Overload of subscribe()**
  ```java
  public final Subscription subscribe(final Action1<? super T> onNext, final Action1<Throwable> onError) {
      if (onNext == null) {
        throw new IllegalArgumentException("onNext can not be null");
      }
      if (onError == null) { 
        throw new IllegalArgumentException("onError can not be null");
      }
      
      Action0 onCompleted = Actions.empty();
      return subscribe(new ActionSubscriber<T>(onNext, onError, onCompleted));
  } 
  ```
  
  ```java
  public final Subscription subscribe(final Observer<? super T> observer) {
      if (observer instanceof Subscriber) {
          return subscribe((Subscriber<? super T>)observer);
      }
      
      return subscribe(new ObserverSubscriber<T>(observer));
  }
  ```
  
  ```java
  public final Subscription subscribe(Subscriber<? super T> subscriber) {
    
      return Observable.subscribe(subscriber, this);
  }
  ```
  
  **The final execution method of subscribe()**
  ```java
  static <T> Subscription subscribe(Subscriber<? super T> subscriber, Observable<T> observable) {
      // Omitted part: null-check

      subscriber.onStart();

      try {
          // allow the hook to intercept and/or decorate
          RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
          return RxJavaHooks.onObservableReturn(subscriber);
      } catch (Throwable e) {
          // special handling for certain Throwable/Error/Exception types
          Exceptions.throwIfFatal(e);
          return Subscriptions.unsubscribed();
      }
  }
  ```
  
  ```java
  public final class RxJavaHooks {
      public static <T> Observable.OnSubscribe<T> onObservableStart(Observable<T> instance, Observable.OnSubscribe<T> onSubscribe) {
          Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> f = onObservableStart;
          if (f != null) {
              return f.call(instance, onSubscribe);
          }
          return onSubscribe;
      }

      public static Subscription onObservableReturn(Subscription subscription) {
          Func1<Subscription, Subscription> f = onObservableReturn;
          if (f != null) {
              return f.call(subscription);
          }
          return subscription;
      }
  }
  ```
  
- ### 3. Thread Scheduler
  
  + ##### 3.1 subscribeOn()
  ```java
  public final Observable<T> subscribeOn(Scheduler scheduler) {
      if (this instanceof ScalarSynchronousObservable) {
          return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
      }
      
      return create(new OperatorSubscribeOn<T>(this, scheduler));
  }
  ```
  + ##### 3.2 observeOn()
  ```
  public final Observable<T> observeOn(Scheduler scheduler) {
      return observeOn(scheduler, RxRingBuffer.SIZE);
  }
  ```

- ### 3. Map
  
  + ##### .1 map()
  ```java
  //new OnSubscribeMap<T, R>(this, func) converts Func1<> into OnSubscribe
  public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
    return unsafeCreate(new OnSubscribeMap<T, R>(this, func));
  }
  ```
  **The key is** *OnSubscribeMap<T, R>*  
  *OnSubscribeMap<T, R>* creates a new subscriber in the method call(), which 
  will subscribe
  
  ```java
  //unsafeCreate() converts the Observable.OnSubscribe<T> into Observable.
  public static <T> Observable<T> unsafeCreate(OnSubscribe<T> f) {
    return new Observable<T>(RxJavaHooks.onCreate(f));
  }
  ```
  
  ```java
  public final class OnSubscribeMap<T, R> implements OnSubscribe<R> {
  
      final Observable<T> source;
  
      final Func1<? super T, ? extends R> transformer;
  
      public OnSubscribeMap(Observable<T> source, Func1<? super T, ? extends R> transformer) {
          this.source = source;
          this.transformer = transformer;
      }
  
      @Override
      public void call(final Subscriber<? super R> o) {
          MapSubscriber<T, R> parent = new MapSubscriber<T, R>(o, transformer);
          o.add(parent);
          source.unsafeSubscribe(parent);
      }
  
      static final class MapSubscriber<T, R> extends Subscriber<T> {
  
          final Subscriber<? super R> actual;
  
          final Func1<? super T, ? extends R> mapper;
  
          boolean done;
  
          public MapSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends R> mapper) {
              this.actual = actual;
              this.mapper = mapper;
          }
  
          @Override
          public void onNext(T t) {
              R result;
  
              try {
                  result = mapper.call(t);
              } catch (Throwable ex) {
                  Exceptions.throwIfFatal(ex);
                  unsubscribe();
                  onError(OnErrorThrowable.addValueAsLastCause(ex, t));
                  return;
              }
  
              actual.onNext(result);
          }
          //Omits onError(Throwable e) and onCompleted() and setProducer(Producer p)
      }
  
  }
  ```

  + ##### .2 subscribeOn()
  ```java
  public final Observable<T> subscribeOn(Scheduler scheduler, boolean requestOn) {
    if (this instanceof ScalarSynchronousObservable) {
      return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
    }
    //Mostly, we use unsafeCreate()
    return unsafeCreate(new OperatorSubscribeOn<T>(this, scheduler, requestOn));
  }
  ```
  
  Similar to OnSubscribeMap<T, R> in method map();
  ```java
  public final class OperatorSubscribeOn<T> implements OnSubscribe<T> {
    
    final Scheduler scheduler;
    final Observable<T> source;
    final boolean requestOn;

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler, boolean requestOn) {
      this.scheduler = scheduler;
      this.source = source;
      this.requestOn = requestOn;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
      final Worker inner = scheduler.createWorker();

      SubscribeOnSubscriber<T> parent = new SubscribeOnSubscriber<T>(subscriber, requestOn, inner, source);
      subscriber.add(parent);
      subscriber.add(inner);

      inner.schedule(parent);
    }

    static final class SubscribeOnSubscriber<T> extends Subscriber<T> implements Action0 {

      final Subscriber<? super T> actual;

      final boolean requestOn;

      final Worker worker;

      Observable<T> source;

      Thread t;

      SubscribeOnSubscriber(Subscriber<? super T> actual, boolean requestOn, Worker worker, Observable<T> source) {
        this.actual = actual;
        this.requestOn = requestOn;
        this.worker = worker;
        this.source = source;
      }

      @Override
      public void onNext(T t) {
        actual.onNext(t);
      }

      @Override
      public void call() {
        Observable<T> src = source;
        source = null;
        t = Thread.currentThread();
        src.unsafeSubscribe(this);
      }

      @Override
      public void setProducer(final Producer p) {
        actual.setProducer(new Producer() {
          @Override
          public void request(final long n) {
            if (t == Thread.currentThread() || !requestOn) {
              p.request(n);
            } else {
              worker.schedule(new Action0() {
                @Override
                public void call() {
                  p.request(n);
                }
              });
            }
          }
        });
      }
    }
  }
  ```
  
  + ##### .3 subscribeOn()
  ```java
  public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
    if (this instanceof ScalarSynchronousObservable) {
      return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
    }
    //Mostly, we use lift()
    return lift(new OperatorObserveOn<T>(scheduler, delayError, bufferSize));
  }
  ```
    
  *Observable.Operator<R, T>*
  ```java
  public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    // cover for generics insanity
  }
  ```
  
  ```java
  
  ```
    
  ```java
  public final <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
    return unsafeCreate(new OnSubscribeLift<T, R>(onSubscribe, operator));
  }
  ```
  
  ```java
  
  ```
  
  ```java
  
  ```