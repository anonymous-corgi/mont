package leetcode.p701to750;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode729MyCalendarI {

    private static abstract class MyCalendar {

        public MyCalendar() {
        }

        abstract public boolean book(int start, int end);
    }

    private static final class TreeMapImpl extends MyCalendar {

        private final TreeMap<Integer, Integer> events = new TreeMap<>();

        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> prev = events.floorEntry(start);
            if (prev != null && start < prev.getValue()) {
                return false;
            }
            Map.Entry<Integer, Integer> next = events.ceilingEntry(start);
            if (next != null && end > next.getKey()) {
                return false;
            }
            events.put(start, end);
            return true;
        }
    }
}
