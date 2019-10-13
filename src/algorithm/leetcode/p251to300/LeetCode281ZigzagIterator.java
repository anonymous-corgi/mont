package algorithm.leetcode.p251to300;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 281. Zigzag Iterator
 * Medium
 *
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 *
 * Example:
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 *
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,3,2,4,5,6].
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
 * If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
 *
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * Output: [1,4,8,2,5,9,3,6,7].
 */
public class LeetCode281ZigzagIterator {

    private static abstract class ZigzagIterator {

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        }

        abstract public int next();

        abstract public boolean hasNext();
    }

    private static final class TwoPointers extends ZigzagIterator {
        private int i1;
        private int i2;
        private final List<Integer> v1;
        private final List<Integer> v2;

        public TwoPointers(List<Integer> v1, List<Integer> v2) {
            super(v1, v2);
            this.v1 = v1;
            this.v2 = v2;
        }

        public int next() {
            if (i1 < v1.size() && i2 < v2.size()) {
                return (i1 <= i2) ? v1.get(i1++) : v2.get(i2++);
            } else if (i1 < v1.size()) {
                return v1.get(i1++);
            } else {
                return v2.get(i2++);
            }
        }

        public boolean hasNext() {
            return i1 < v1.size() || i2 < v2.size();
        }
    }

    private static final class Dequeue extends ZigzagIterator {

        private static final class Pair {
            int listIndex;
            int itemIndex;

            private Pair(int listIndex, int itemIndex) {
                this.listIndex = listIndex;
                this.itemIndex = itemIndex;
            }
        }

        private final List<List<Integer>> originList;
        private final Deque<Pair> taskQueue;

        public Dequeue(List<Integer> v1, List<Integer> v2) {
            super(v1, v2);
            this.originList = Arrays.asList(v1, v2);
            this.taskQueue = new ArrayDeque<>(2);
            for (int i = 0; i < originList.size(); i++) {
                if (!originList.get(i).isEmpty()) {
                    taskQueue.offer(new Pair(i, 0));
                }
            }
        }

        public int next() {
            Pair pair = taskQueue.poll();
            List<Integer> list = originList.get(pair.listIndex);
            int item = list.get(pair.itemIndex);
            if (++pair.itemIndex < list.size()) {
                taskQueue.offer(pair);
            }
            return item;
        }

        public boolean hasNext() {
            return !taskQueue.isEmpty();
        }
    }
}