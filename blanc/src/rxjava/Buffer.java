package rxjava;

import io.reactivex.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Buffer {

    public static void main(String[] args) {
        bufferAndDebounce();
    }

    /**
     * @see <a href="Rxjava.Buffer">http://reactivex.io/documentation/operators/buffer.html</a>
     */
    static void bufferAndDebounce() {
        Observable<Integer> bursty = Observable
                .intervalRange(1, 5, 0, 20, TimeUnit.MILLISECONDS)
                .flatMap(aLong -> Observable.range(aLong.intValue() * 10, 10));

        // we have to multicast the original bursty Observable so we can use it
        // both as our source and as the source for our buffer closing selector:
        Observable<Integer> burstyMulticast = bursty.publish().refCount();
        // burstyDebounced will be our buffer closing selector:
        Observable<Integer> burstyDebounced = burstyMulticast.debounce(10, TimeUnit.MILLISECONDS);
        // and this, finally, is the Observable of buffers we're interested in:
        Observable<List<Integer>> burstyBuffered = burstyMulticast.buffer(burstyDebounced);

        burstyBuffered.blockingSubscribe(System.out::println);
    }
}
