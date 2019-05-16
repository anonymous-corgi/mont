package rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Throttle {

    private static long initTime;

    private static long getCurrentTime() {
        return (System.currentTimeMillis() - initTime) / 10;
    }

    public static void main(String[] args) {
        throttleLatest();
    }

    // Example: Every first click will act and start a timeout to block the following clicks.
    private static void throttleFirst() {
        createInterval()
                .throttleFirst(40, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println(
                                "Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count
                                        + "\t Receive <-");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Observer onComplete() at Time: " + getCurrentTime());
                    }
                });
    }

    // Example: Every click will start or restart a timer to do the action
    private static void throttleWithTimeout() {
        createInterval()
                .throttleWithTimeout(40, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println(
                                "Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count
                                        + "\t Receive <-");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Observer onComplete() at Time: " + getCurrentTime());
                    }
                });
    }

    // throttleLast() pass down onNext() at fixed period.
    private static void throttleLast() {
        createInterval()
                .throttleLast(40, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println(
                                "Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count
                                        + "\t Receive <-");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Observer onComplete() at Time: " + getCurrentTime());
                    }
                });
    }

    // throttleLatest() limits maximum that only one emission in a time span.
    private static void throttleLatest() {
        createInterval()
                .throttleLatest(40, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println(
                                "Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count
                                        + "\t Receive <-");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Observer onComplete() at Time: " + getCurrentTime());
                    }
                });
    }

    @Test
    public void createLocationDebouncePublisher() {
        TestObserver<List<Long>> test = new TestObserver<>();
        PublishSubject<Long> publisher = PublishSubject.create();

        createInterval().subscribe(publisher);

        publisher.buffer(publisher.debounce(500, TimeUnit.MILLISECONDS).take(1))
                .take(2)
                .blockingSubscribe(test);


        test.assertComplete();
    }

    private static Observable<Long> createInterval() {
        return Observable.merge(Observable.intervalRange(0, 7, 0, 60, TimeUnit.MILLISECONDS),
                Observable.intervalRange(0, 4, 0, 90, TimeUnit.MILLISECONDS))
                .doOnSubscribe(disposable -> initTime = System.currentTimeMillis())
                .doOnNext(count -> System.out.println(
                        "Observable doOnNext() at Time:" + getCurrentTime() + ",\tCount:" + count
                                + "\t Send ->"));
    }
}
