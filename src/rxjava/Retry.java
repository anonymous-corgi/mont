package rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Retry {

    public static void main(String[] args) {
        retryWhen();
//        Observable
//                .create(new ObservableOnSubscribe<Integer>() {
//                    int count = 0;
//                    int time = 3;
//
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        System.out.println("Subscribe Count: " + count);
//                        if (count < time) {
//                            emitter.onError(new IOException());
//                        } else {
//                            emitter.onNext(time);
//                            emitter.onComplete();
//                        }
//                        emitter.onNext(count);
//                        count++;
//                    }
//                })
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                        System.out.println("retryWhen Count: ");
//                        return Observable.just(1);
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("Observer onNext: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("Observer onError()");
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("Observer onComplete()");
//                    }
//                });
    }

    static void retryWhen() {
        Observable
                .timer(1, TimeUnit.SECONDS)
                .doOnSubscribe(s -> System.out.println("subscribing"))
                .map(v -> {
                    throw new RuntimeException();
                })
                .retryWhen(errors -> {
                    AtomicInteger counter = new AtomicInteger();
                    return errors
                            .takeWhile(e -> counter.getAndIncrement() != 3)
                            .flatMap(e -> {
                                System.out.println("delay retry by " + counter.get() + " second(s)");
                                return Observable.timer(counter.get(), TimeUnit.SECONDS);
                            });
                })
                .blockingSubscribe(System.out::println, System.out::println);
    }

    static void retry_BiPredicate() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    int count = 0;
                    int time = 3;

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        System.out.println("Subscribe Count: " + count);
                        if (count < time) {
                            emitter.onError(new IOException());
                        } else {
                            emitter.onNext(time);
                            emitter.onComplete();
                        }
                        emitter.onNext(count);
                        count++;
                    }
                })
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(Integer integer, Throwable throwable) throws Exception {
                        System.out.println("test Count: " + integer);
                        return (integer < 3);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Observer onNext(): " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Observer onError()");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Observer onComplete()");
                    }
                });
    }
}
