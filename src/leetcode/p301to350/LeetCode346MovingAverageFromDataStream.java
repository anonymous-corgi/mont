package leetcode.p301to350;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 346. Moving Average from Data Stream
 * Easy
 *
 * Given a stream of integers and a window size,
 * calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class LeetCode346MovingAverageFromDataStream {

    private static abstract class MovingAverage {

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
        }

        abstract public double next(int val);
    }


    private static final class Deque_Method extends MovingAverage {

        private long mSum;
        private final int mMaxSize;
        private final Deque<Integer> mDeque;

        public Deque_Method(int size) {
            super(size);
            mMaxSize = size;
            mDeque = new ArrayDeque<>(size);
        }

        @Override
        public double next(int val) {
            if (mDeque.size() == mMaxSize) {
                mSum -= mDeque.poll();
            }
            mDeque.offer(val);
            mSum += val;
            return (double) mSum / mDeque.size();
        }
    }
}
