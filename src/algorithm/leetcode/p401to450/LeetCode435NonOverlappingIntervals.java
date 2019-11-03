package algorithm.leetcode.p401to450;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode435NonOverlappingIntervals {

    private interface Method {
        int eraseOverlapIntervals(int[][] intervals);
    }

    private static class Greedy implements Method {

        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            int count = 0;
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
            int lastEnd = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= lastEnd) {
                    lastEnd = intervals[i][1];
                } else {
                    count++;
                }
            }
            return count;
        }
    }

    private static Method getMethod() {
        return new Greedy();
    }

    private void test(int[][] intervals, int expected) {
        int actual = getMethod().eraseOverlapIntervals(intervals);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[][] intervals = new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        test(intervals, 2);
    }

    @Test
    public void testcase2() {
        int[][] intervals = new int[][]{{1, 100}, {11, 20}, {1, 10}};
        test(intervals, 1);
    }
}
