package rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Throttle {

    private static long initTime;

    private static long getCurrentTime() {
        return System.currentTimeMillis() - initTime;
    }

    public static void main(String[] args) {
        throttleFirst();
    }

    private static void throttleFirst() {
        Observable.merge(Observable.intervalRange(0, 13, 0, 50, TimeUnit.MILLISECONDS),
                Observable.intervalRange(0, 6, 0, 75, TimeUnit.MILLISECONDS))
                .doOnSubscribe(disposable -> initTime = System.currentTimeMillis())
                .doOnNext(count -> System.out.println("Observable doOnNext() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Send ->"))
                .throttleFirst(30, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println("Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Receive <-");
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

    private static void throttleWithTimeout() {
        Observable
                .intervalRange(0, 13, 0, 50, TimeUnit.MILLISECONDS)
                .doOnSubscribe(disposable -> initTime = System.currentTimeMillis())
                .doOnNext(count -> System.out.println("Observable doOnNext() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Send ->"))
                .throttleWithTimeout(120, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println("Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Receive <-");
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
        Observable
                .intervalRange(0, 13, 0, 50, TimeUnit.MILLISECONDS)
                .doOnSubscribe(disposable -> initTime = System.currentTimeMillis())
                .doOnNext(count -> System.out.println("Observable doOnNext() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Send ->"))
                .throttleLast(30, TimeUnit.MILLISECONDS)
                .blockingSubscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long count) {
                        System.out.println("Observer onComplete() at Time:" + getCurrentTime() + ",\tCount:" + count + "\t Receive <-");
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

}
