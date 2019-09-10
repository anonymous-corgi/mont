package leetcode.p001to050;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("unused")
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
     * This method is similar to the {@link leetcode.p101to150.LeetCode135Candy}
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

    private static Method getMethod() {
        return new Candy();
    }

    private void test(int[] heights, int expected) {
        Method method = getMethod();
        int actual = method.trap(heights);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] heights = {1, 0, 1};
        test(heights, 1);
    }

    @Test
    public void testcase2() {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        test(heights, 6);
    }
}
