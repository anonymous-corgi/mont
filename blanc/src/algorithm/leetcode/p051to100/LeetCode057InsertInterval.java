package algorithm.leetcode.p051to100;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Similar to {@link LeetCode056MergeIntervals}
 */
public class LeetCode057InsertInterval {

    private interface Method {
        int[][] insert(int[][] intervals, int[] newInterval);
    }

    private static class Pointer implements Method {

        @Override
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals == null) {
                return new int[0][];
            }
            List<int[]> builder = new ArrayList<>(intervals.length + 1);
            for (int[] interval : intervals) {
                if (newInterval == null || interval[1] < newInterval[0]) {
                    builder.add(interval);
                } else if (newInterval[1] < interval[0]) {
                    builder.add(newInterval);
                    builder.add(interval);
                    newInterval = null;
                } else {
                    newInterval = new int[]{Math.min(interval[0], newInterval[0]),
                            Math.max(interval[1], newInterval[1])};
                }
            }
            if (newInterval != null) {
                builder.add(newInterval);
            }
            return builder.toArray(new int[0][]);
        }
    }

    private static Method getMethod() {
        return new Pointer();
    }

    @Test
    public void testcase1() {
        int[][] intervals = new int[0][];
        int[] newInterval = new int[]{5, 7};
        int[][] expect = new int[][]{{5, 7}};
        testcase(intervals, newInterval, expect);
    }

    private void testcase(int[][] intervals, int[] newInterval, int[][] expect) {
        Assert.assertThat(getMethod().insert(intervals, newInterval), is(expect));
    }
}
