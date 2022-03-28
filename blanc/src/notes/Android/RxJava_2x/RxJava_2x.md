# RxJava_2x

- ### Reference Websites:

- ### 0. My Summary

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

  + ##### io.reactivex: ***Observer\<T\>***
  ```java
  public interface Observer<T> {

    /**
     * Provides the Observer with the means of cancelling (disposing) the
     * connection (channel) with the Observable in both
     * synchronous (from within {@link #onNext(Object)}) and asynchronous manner.
     * @param d the Disposable instance whose {@link Disposable#dispose()} can
     * be called anytime to cancel the connection
     * @since 2.0
     */
    void onSubscribe(@NonNull Disposable d);

    /**
     * Provides the Observer with a new item to observe.
     * <p>
     * The {@link Observable} may call this method 0 or more times.
     * <p>
     * The {@code Observable} will not call this method again after it calls either {@link #onComplete} or
     * {@link #onError}.
     *
     * @param t
     *          the item emitted by the Observable
     */
    void onNext(@NonNull T t);

    /**
     * Notifies the Observer that the {@link Observable} has experienced an error condition.
     * <p>
     * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
     * {@link #onComplete}.
     *
     * @param e
     *          the exception encountered by the Observable
     */
    void onError(@NonNull Throwable e);

    /**
     * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
     * <p>
     * The {@link Observable} will not call this method if it calls {@link #onError}.
     */
    void onComplete();
  }
  ```

  + ##### io.reactivex.disposables: ***Disposable***
  ```java
  public interface Disposable {
    /**
     * Dispose the resource, the operation should be idempotent.
     */
    void dispose();

    /**
     * Returns true if this resource has been disposed.
     * @return true if this resource has been disposed
     */
    boolean isDisposed();
  }
  ```

  + ##### io.reactivex: ***Emitter\<T\>***
  ```java
  public interface Emitter<T> {

      /**
       * Signal a normal value.
       * @param value the value to signal, not null
       */
      void onNext(@NonNull T value);

      /**
       * Signal a Throwable exception.
       * @param error the Throwable to signal, not null
       */
      void onError(@NonNull Throwable error);

      /**
       * Signal a completion.
       */
      void onComplete();
  }
  ```

  + ##### io.reactivex: ***ObservableSource\<T\>***
  ```java
  public interface ObservableSource<T> {

      /**
       * Subscribes the given Observer to this ObservableSource instance.
       * @param observer the Observer, not null
       * @throws NullPointerException if {@code observer} is null
       */
      void subscribe(@NonNull Observer<? super T> observer);
  }
  ```

  + ##### io.reactivex: ***Scheduler***
  ```java
  public abstract class Scheduler {

    public abstract Worker createWorker();

    public abstract static class Worker implements Disposable {

      @NonNull
      public Disposable schedule(@NonNull Runnable run) {
          return schedule(run, 0L, TimeUnit.NANOSECONDS);
      }

      @NonNull
      public abstract Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit);

      @NonNull
      public Disposable schedulePeriodically(@NonNull Runnable run, final long initialDelay, final long period, @NonNull final TimeUnit unit) {
          final SequentialDisposable first = new SequentialDisposable();

          final SequentialDisposable sd = new SequentialDisposable(first);

          final Runnable decoratedRun = RxJavaPlugins.onSchedule(run);

          final long periodInNanoseconds = unit.toNanos(period);
          final long firstNowNanoseconds = now(TimeUnit.NANOSECONDS);
          final long firstStartInNanoseconds = firstNowNanoseconds + unit.toNanos(initialDelay);

          Disposable d = schedule(new PeriodicTask(firstStartInNanoseconds, decoratedRun, firstNowNanoseconds, sd,
                  periodInNanoseconds), initialDelay, unit);

          if (d == EmptyDisposable.INSTANCE) {
              return d;
          }
          first.replace(d);

          return sd;
      }
    }
  }
  ```

- ### 2. io.reactivex: ***Observable\<T\>***
  ```java
  public abstract class Observable<T> implements ObservableSource<T> {
  
    public final void subscribe(Observer<? super T> observer) {
      ObjectHelper.requireNonNull(observer, "observer is null");
      try {
          observer = RxJavaPlugins.onSubscribe(this, observer);
  
          ObjectHelper.requireNonNull(observer, "Plugin returned null Observer");
  
          subscribeActual(observer);
      } catch (NullPointerException e) { // NOPMD
          throw e;
      } catch (Throwable e) {
          Exceptions.throwIfFatal(e);
          // can't call onError because no way to know if a Disposable has been set or not
          // can't call onSubscribe because the call might have set a Subscription already
          RxJavaPlugins.onError(e);
  
          NullPointerException npe = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
          npe.initCause(e);
          throw npe;
      }
    }
  
    protected abstract void subscribeActual(Observer<? super T> observer);
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

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = source;
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

      Observable<T> source

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

      @Override // Action0
      public void call() {
        Observable<T> src = source;
        source = null;
        src.unsafeSubscribe(this);
      }
    }
  }
  ```

- ### . Lift

  + ##### .1 lift()
  ```java
  public final <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
    //create() is converting a OnSubscribe<R> into a Observable<R>, isn't the key.
    return create(new OnSubscribeLift<T, R>(onSubscribe, operator));
  }
  ```

  *Observable.Operator<R, T>*
  ```java
  public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    // cover for generics insanity
  }
  ```

  ```java
  public final class OnSubscribeLift<T, R> implements OnSubscribe<R> {

    final OnSubscribe<T> parent;

    final Operator<? extends R, ? super T> operator;

    public OnSubscribeLift(OnSubscribe<T> parent, Operator<? extends R, ? super T> operator) {
        this.parent = parent;
        this.operator = operator;
    }

    @Override
    public void call(Subscriber<? super R> o) {
      try {
          Subscriber<? super T> st = RxJavaHooks.onObservableLift(operator).call(o);
          try {
              st.onStart();
              parent.call(st);
          } catch (Throwable e) {
              Exceptions.throwIfFatal(e);
              st.onError(e);
          }
      } catch (Throwable e) {
          Exceptions.throwIfFatal(e);
          o.onError(e);
      }
    }
  }
  ```

  + ##### .2 observeOn()
  ```java
  public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
    if (this instanceof ScalarSynchronousObservable) {
      return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
    }
    //Mostly, we use lift()
    return lift(new OperatorObserveOn<T>(scheduler, delayError, bufferSize));
  }
  ```

  ```java
  public final class OperatorObserveOn<T> implements Operator<T, T> {

    private final Scheduler scheduler;
    private final boolean delayError;
    private final int bufferSize;

    public OperatorObserveOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        this.scheduler = scheduler;
        this.delayError = delayError;
        this.bufferSize = (bufferSize > 0) ? bufferSize : RxRingBuffer.SIZE;
    }

    @Override
    public Subscriber<? super T> call(Subscriber<? super T> child) {
      ObserveOnSubscriber<T> parent = new ObserveOnSubscriber<T>(scheduler, child, delayError, bufferSize);
      parent.init();
      return parent;
    }

    static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {

      final Subscriber<? super T> child;
      final Scheduler.Worker recursiveScheduler;

      public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> child, boolean delayError, int bufferSize) {
        this.child = child;
        this.recursiveScheduler = scheduler.createWorker();
      }

      @Override
      public void onNext(final T t) {
        schedule();
      }

      protected void schedule() {
        if (counter.getAndIncrement() == 0) {
            recursiveScheduler.schedule(this);
        }
      }

      @Override
      public void call() {
        final Subscriber<? super T> localChild = this.child;
        localChild.onNext(NotificationLite.<T>getValue(v));
      }
    }
  }
  ```
- ### . Merge()

  Merge() will firstly create an Observable<Observable<T\>\> from a sequence of Observable<T\>
  ```java
  public static <T> Observable<T> merge(Observable<? extends T>[] sequences) {
      return merge(from(sequences));
  }
  ```

  ```java
  public static <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source) {
    if (source.getClass() == ScalarSynchronousObservable.class) {
        return ((ScalarSynchronousObservable<T>)source).scalarFlatMap((Func1)UtilityFunctions.identity());
    }
    return source.lift(OperatorMerge.<T>instance(false));
  }
  ```
  Inside OperatorMerge<T\> there is a MergeSubscriber<T\> that will subscribe the Parent Observable and emit element T to its child Observable.
  ```java
  public final class OperatorMerge<T> implements Operator<T, Observable<? extends T>> {

    public static <T> OperatorMerge<T> instance(boolean delayErrors, int maxConcurrent) {
      return new OperatorMerge<T>(delayErrors, maxConcurrent);
    }

    @Override
    public Subscriber<Observable<? extends T>> call(final Subscriber<? super T> child) {
      MergeSubscriber<T> subscriber = new MergeSubscriber<T>(child, delayErrors, maxConcurrent);
      MergeProducer<T> producer = new MergeProducer<T>(subscriber);
      subscriber.producer = producer;

      child.add(subscriber);
      child.setProducer(producer);

      return subscriber;
    }

    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {

      final Subscriber<? super T> child;
      final boolean delayErrors;
      final int maxConcurrent;

      public MergeSubscriber(Subscriber<? super T> child, boolean delayErrors, int maxConcurrent) {
        this.child = child;
        this.delayErrors = delayErrors;
        this.maxConcurrent = maxConcurrent;
      }

      @Override
      public void onNext(Observable<? extends T> t) {
          if (t == null) {
              return;
          }
          if (t == Observable.empty()) {
              emitEmpty();
          } else
          if (t instanceof ScalarSynchronousObservable) {
              tryEmit(((ScalarSynchronousObservable<? extends T>)t).get());
          } else {
              InnerSubscriber<T> inner = new InnerSubscriber<T>(this, uniqueId++);
              addInner(inner);
              t.unsafeSubscribe(inner);
              emit();
          }
      }

      void emitLoop() {
        child.onNext(v);
      }

    }

  ```

- ### . FlatMap()
  FlatMap() uses Func1 creating multiple Observable and then uses *merge()* to merge them into one
  ```java
  public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
    if (getClass() == ScalarSynchronousObservable.class) {
      return ((ScalarSynchronousObservable<T>)this).scalarFlatMap(func);
    }
    return merge(map(func));
  }
  ```

  ```java
  public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
    return create(new OnSubscribeMap<T, R>(this, func));
  }
  ```

  ```java
  public static <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source) {
    if (source.getClass() == ScalarSynchronousObservable.class) {
      return ((ScalarSynchronousObservable<T>)source).scalarFlatMap((Func1)UtilityFunctions.identity());
    }
    return source.lift(OperatorMerge.<T>instance(false));
  }
  ```

  ```java

  ```

- ### Interval
  + **interval()**: Returns an Observable that emits a sequential number every specified interval of time, on a specified Scheduler. **NOTE: won't stop itself**
  + **intervalRange()**: Signals a range of long values, the first after some initial delay and the rest periodically after.

- ### CombineLatest
  + **combineLatest**: Combines a collection of source ObservableSources by emitting an item that aggregates the latest values of each of the source ObservableSources each time an item is received from any of the source ObservableSources, where this aggregation is defined by a specified function.
  + **withLatestFrom**: Merges the specified ObservableSource into this ObservableSource sequence by using the {@code resultSelector} function only when the source ObservableSource (this instance) emits an item.

- ### Throttle
  + **throttleFirst()**: Returns an Observable that emits only the first item emitted by the source ObservableSource during sequential time windows of a specified duration.
  **NOTE: throttleFirst is different from throttleLast. it is triggered by the first onNext and block the following onNext() in the time window. It reopens to pass onNext() after that time window (this onNext() is receive after that time freezing window)**

  + **throttleWithTimeout() == debounce(long timeout, TimeUnit unit)**: Returns an Observable that mirrors the source ObservableSource, except that it drops items emitted by the source ObservableSource that are followed by newer items before a timeout value expires. The timer resets on each emission.**NOTE: in logic it more like throttleFirst**

  + **throttleLast() == sample(long period, TimeUnit unit)**: Returns an Observable that emits only the last item emitted by the source ObservableSource during sequential time windows of a specified duration.

  + **throttleLatest()**: **NOTE: work as throttleLast(), but throttleLast() doesn't emits first item while throttleLatest does.**

  + **sample(ObservableSource\<U\> sampler)**: Returns an Observable that emits the most recently emitted item (if any) emitted by the source ObservableSource within periodic time intervals or triggered by a sampler Observable.

- ### Retry
  + **retry(long times, Predicate\<? super Throwable\> predicate)**: Retries at most times or until the predicate returns false, whichever happens first.

  + **retry(BiPredicate\<? super Integer, ? super Throwable\> predicate)**: Returns an Observable that mirrors the source ObservableSource, resubscribing to it if it calls {@code onError} and the predicate returns true for that specific exception and retry count. **NOTE: predicate passes the retry count and Throwable for you to decide whether to retry.**

  + **retryUntil** is a simplified version of retry(BiPredicate predicate)
