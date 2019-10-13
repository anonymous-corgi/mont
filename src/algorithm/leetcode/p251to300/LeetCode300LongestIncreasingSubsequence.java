package algorithm.leetcode.p251to300;

import java.util.Arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode300LongestIncreasingSubsequence {

    private interface Method {
        int lengthOfLIS(int[] nums);
    }

    private static final class DP_n2 implements Method {

        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int max = 1;
            int[] dp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    private static final class DP_nlogn implements Method {

        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                // Find the index of the first element that is not larger than num.
                int idx = Arrays.binarySearch(dp, 0, len, num);
                if (idx < 0) {
                    idx = -(idx + 1);
                }
                dp[idx] = num;

                if (idx == len) {
                    len++;
                }
            }
            return len;
        }
    }

    private Method getMethod() {
        return new DP_n2();
    }

    private void test(int[] nums, int expected) {
        int actual = getMethod().lengthOfLIS(nums);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        test(nums, 4);
    }

    @Test
    public void testcase2() {
        int[] nums = {88, 4, 24, 82, 86, 1, 56, 74, 71, 9, 8, 18, 26, 53, 77, 87, 60, 27, 69, 17, 76, 23, 67, 14, 98, 13, 10, 83, 20, 43, 39, 29, 92, 31, 0, 30, 90, 70, 37, 59};
        test(nums, 10);
    }
}
