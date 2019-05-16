package rxjava;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Window {

    private static long initTime;

    private static long getCurrentTime() {
        return (System.currentTimeMillis() - initTime) / 10;
    }

    public static void main(String[] args) {
        widow_combine();
    }

    static void window_timespan() {
        Flowable.intervalRange(2, 5, 0, 30, TimeUnit.MILLISECONDS)
                .flatMap(num -> Flowable.range(num.intValue() * 5, 5))
                .window(10, TimeUnit.MILLISECONDS)
                .flatMapSingle(Flowable::toList)
                .blockingSubscribe(new DisposableSubscriber<List<Integer>>() {
                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println(integers);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    static void window() {
        Flowable.intervalRange(2, 5, 0, 30, TimeUnit.MILLISECONDS)
                .flatMap(num -> Flowable.range(num.intValue() * 5, 5))
                .window(() -> Flowable.timer(50, TimeUnit.MILLISECONDS))
                .flatMapSingle(Flowable::toList)
                .blockingSubscribe(new DisposableSubscriber<List<Integer>>() {
                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println(integers);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    static void widow_combine() {
        PublishSubject<Long> publisher = PublishSubject.create();

        publisher.buffer(publisher.throttleFirst(100, TimeUnit.MILLISECONDS).flatMap(l ->
                Observable.timer(100, TimeUnit.MILLISECONDS)))
                .subscribe(new Observer<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe()");
                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        System.out.println(
                                "Observer onNext() at Time:" + getCurrentTime() + ",\tget:" + longs.size()
                                        + "\t Receive <-");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError()");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });


        createInterval().blockingSubscribe(publisher);
    }

    private static Observable<Long> createInterval() {
        return Observable.merge(Observable.intervalRange(0, 9, 0, 150, TimeUnit.MILLISECONDS),
                Observable.intervalRange(0, 7, 0, 200, TimeUnit.MILLISECONDS),
                Observable.intervalRange(0, 5, 0, 300, TimeUnit.MILLISECONDS),
                Observable.intervalRange(0, 4, 0, 400, TimeUnit.MILLISECONDS))
                .doOnSubscribe(disposable -> initTime = System.currentTimeMillis())
                .doOnSubscribe(disposable -> System.out.println("Subscribed"))
                .doOnNext(count -> System.out.println(
                        "Observable doOnNext() at Time:" + getCurrentTime() + "\t Send ->"));
    }
}
