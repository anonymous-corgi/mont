package rxjava;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Window {

    public static void main(String[] args) {
        window();
    }

    static void window() {
        Flowable.intervalRange(2, 5, 0, 20, TimeUnit.MILLISECONDS)
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
}
