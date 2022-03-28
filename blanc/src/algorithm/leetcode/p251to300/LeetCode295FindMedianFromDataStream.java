package algorithm.leetcode.p251to300;

import java.util.PriorityQueue;

public class LeetCode295FindMedianFromDataStream {

    private interface MedianFinder {

        void addNum(int num);

        double findMedian();
    }

    private static class Impl implements MedianFinder {
        private final PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> b - a);
        private final PriorityQueue<Integer> large = new PriorityQueue<>();

        @Override
        public void addNum(int num) {
            large.offer(num);
            small.offer(large.poll());
            if (small.size() > large.size()) {
                large.offer(small.poll());
            }
        }

        @Override
        public double findMedian() {
            int size = small.size() + large.size();
            if (size % 2 == 1) {
                return (double) large.peek();
            } else {
                return (small.peek() + large.peek()) / 2d;
            }
        }
    }
}
