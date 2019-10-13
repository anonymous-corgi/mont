package algorithm.leetcode.p701to750;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode731MyCalendarII {

    private static abstract class MyCalendarTwo {

        public MyCalendarTwo() {
        }

        abstract public boolean book(int start, int end);
    }

    //Method 1:
    public static final class TreeMapImpl extends MyCalendarTwo {

        private final TreeMap<Integer, Integer> events = new TreeMap<>();

        public boolean book(int start, int end) {
            events.put(start, events.getOrDefault(start, 0) + 1);
            events.put(end, events.getOrDefault(end, 0) - 1);
            int count = 0;
            for (Integer v : events.values()) {
                count += v;
                if (count == 3) {
                    events.put(start, events.get(start) - 1);
                    events.put(end, events.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }

    // Two intervals overlap: [s1, e1], [s2, e2] => max(s1, s2) < min(e1, e2)
    //Method 2:
    public static final class ListImpl extends MyCalendarTwo {

        private final List<int[]> events = new ArrayList<>();

        public boolean book(int start, int end) {
            // overlaps should not be reused.
            // There might be an event who has a valid overlap and then a invalid overlap,
            // the booking will fail, but the first overlap is saved in the Overlap.
            Overlap overlaps = new Overlap();
            for (int[] b : events) {
                if (Math.max(b[0], start) < Math.min(b[1], end)) {
                    // overlap exists
                    if (!overlaps.book(Math.max(b[0], start), Math.min(b[1], end))) {
                        return false; // overlaps overlapped
                    }
                }
            }
            events.add(new int[]{start, end});
            return true;
        }

        private static class Overlap {

            private final List<int[]> overlaps = new ArrayList<>();

            boolean book(int start, int end) {
                for (int[] overlap : overlaps) {
                    if (Math.max(overlap[0], start) < Math.min(overlap[1], end)) {
                        return false;
                    }
                }
                overlaps.add(new int[]{start, end});
                return true;
            }
        }
    }

    private static MyCalendarTwo getMethod() {
        return new ListImpl();
    }

    private void test(int[][] events, boolean[] expected) {
        MyCalendarTwo impl = getMethod();
        boolean[] actual = new boolean[expected.length];
        for (int i = 0; i < events.length; i++) {
            actual[i] = impl.book(events[i][0], events[i][1]);
        }
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[][] events = new int[][]{{2, 4}, {6, 8}, {0, 10}, {3, 4}, {4, 6}, {5, 6}, {7, 8}};
        boolean[] expected = new boolean[]{true, true, true, false, true, false, false};
        test(events, expected);
    }

    @Test
    public void testcase2() {
        int[][] events = new int[][]{{36, 41}, {28, 34}, {40, 46}, {10, 18}, {4, 11}, {25, 34}, {36, 44}, {32, 40}, {34, 39}, {40, 49}};
        boolean[] expected = new boolean[]{true, true, true, true, true, true, false, false, true, false};
        test(events, expected);
    }
}
