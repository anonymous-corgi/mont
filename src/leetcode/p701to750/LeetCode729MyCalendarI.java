package leetcode.p701to750;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode729MyCalendarI {

    private interface Method {
        boolean book(int start, int end);
    }

    private static class TreeMap_Method implements Method {

        private final TreeMap<Integer, Integer> timeline = new TreeMap<>();

        @Override
        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> prev = timeline.floorEntry(start);
            Map.Entry<Integer, Integer> next = timeline.ceilingEntry(start);
            if ((prev == null || prev.getValue() <= start)
                    && (next == null || next.getKey() >= end)) {
                timeline.put(start, end);
                return true;
            }
            return false;
        }
    }
}
