package leetcode.p051to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Similar to {@link LeetCode057InsertInterval}
 */
@SuppressWarnings("unused")
public class LeetCode056MergeIntervals {

    private interface Method {
        int[][] merge(int[][] intervals);
    }

    private static class Last implements Method {

        @Override
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return new int[0][];
            }
            Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            int[] last = intervals[0];
            List<int[]> builder = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                if (Math.max(last[0], intervals[i][0]) <= Math.min(last[1], intervals[i][1])) {
                    last[0] = Math.min(last[0], intervals[i][0]);
                    last[1] = Math.max(last[1], intervals[i][1]);
                } else {
                    builder.add(last);
                    last = intervals[i];
                }
            }
            builder.add(last);
            return builder.toArray(new int[0][]);
        }
    }
}