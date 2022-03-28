package algorithm.leetcode.p701to750;

import java.util.TreeMap;

public class LeetCode732MyCalendarIII {

    private static abstract class MyCalendarThree {

        public MyCalendarThree() {
        }

        abstract public int book(int start, int end);
    }

    private static final class TreeMapImpl extends MyCalendarThree {

        private final TreeMap<Integer, Integer> events = new TreeMap<>();

        public int book(int start, int end) {
            events.compute(start, (k, v) -> v == null ? 1 : v + 1);
            events.compute(end, (k, v) -> v == null ? -1 : v - 1);
            int max = 0;
            int count = 0;
            for (int v : events.values()) {
                count += v;
                max = Math.max(max, count);
            }
            return max;
        }
    }
}
