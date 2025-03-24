package algorithm.leetcode.p001to050;

public class LeetCode042TrappingRainWater {

    private interface Method {
        int trap(int[] heights);
    }

    private static final class TwoPointers implements Method {

        @Override
        public int trap(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
            int left = 0;
            int right = heights.length - 1;
            int lHeight = heights[0];
            int rHeight = heights[right];
            int res = 0;

            while (left < right) {
                lHeight = Math.max(lHeight, heights[left]);
                rHeight = Math.max(rHeight, heights[right]);

                if (heights[left] < heights[right]) {
                    res += lHeight - heights[left++];
                } else {
                    res += rHeight - heights[right--];
                }
            }
            return res;
        }
    }

    /**
     * This method is similar to the {@link algorithm.leetcode.p101to150.LeetCode135Candy}
     */
    private static final class Candy implements Method {

        @Override
        public int trap(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
            int total = 0;
            int[] leftMaxHeight = new int[heights.length];
            int[] rightMaxHeight = new int[heights.length];
            leftMaxHeight[0] = heights[0];
            rightMaxHeight[heights.length - 1] = heights[heights.length - 1];
            for (int i = 1; i < heights.length; i++) {
                leftMaxHeight[i] = Math.max(leftMaxHeight[i - 1], heights[i]);
            }
            for (int i = heights.length - 2; i >= 0; i--) {
                rightMaxHeight[i] = Math.max(rightMaxHeight[i + 1], heights[i]);
            }
            for (int i = 0; i < heights.length; i++) {
                total += Math.min(rightMaxHeight[i], leftMaxHeight[i]) - heights[i];
            }
            return total;
        }
    }
}
