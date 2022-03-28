package rxjava;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.TestObserver;

public class Retry {

    @Test
    public void retry_BiPredicate() {
        TestObserver<Object> test = new TestObserver<>();
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    int count = 1;

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        if (count++ < 3) {
                            emitter.onError(new IOException());
                        } else {
                            emitter.onNext(Integer.MAX_VALUE);
                            emitter.onComplete();
                        }
                    }
                })
                .retry((times, e) -> times < 3 && e instanceof IOException)
                .subscribe(test);

        test.assertValue(Integer.MAX_VALUE);
        test.assertComplete();
    }

    @Test
    public void retryWhen() {
        TestObserver<Object> test = new TestObserver<>();
        Observable.create(emitter -> emitter.onError(new RuntimeException()))
                .doOnSubscribe(s -> System.out.println("Subscribing..."))
                .retryWhen(errors -> {
                    AtomicInteger counter = new AtomicInteger(0);
                    return errors
                            .takeWhile(e -> counter.getAndIncrement() != 3)
                            .doOnNext(e -> System.out.println("Delay retry by " + counter.get() + " second(s)"))
                            .flatMap(e -> Observable.timer(counter.get(), TimeUnit.SECONDS))
                            .doOnNext(num -> System.out.println("Intermediate Observer onNext()"))
                            .doOnComplete(() -> System.out.println("Intermediate Observer onComplete()"));
                })
                .blockingSubscribe(test);

        test.assertComplete();
    }
}
