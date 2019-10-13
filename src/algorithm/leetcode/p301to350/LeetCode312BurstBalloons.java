package algorithm.leetcode.p301to350;

import org.junit.Assert;
import org.junit.Test;

public class LeetCode312BurstBalloons {

    private interface Method {
        int maxCoins(int[] nums);
    }

    private static class DP_Method implements Method {

        @Override
        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] arr = new int[nums.length + 2];
            System.arraycopy(nums, 0, arr, 1, nums.length);
            arr[0] = arr[arr.length - 1] = 1;
            int[][] dp = new int[nums.length + 2][nums.length + 3];
            for (int len = 1; len <= nums.length; len++) {
                for (int start = 1, end; (end = start + len) <= (arr.length - 2) + 1; start++) {
                    for (int pick = start; pick < end; pick++) {
                        int sum = dp[start][pick] + arr[start - 1] * arr[pick] * arr[end] + dp[pick + 1][end];
                        dp[start][end] = Math.max(dp[start][end], sum);
                    }
                }
            }
            return dp[1][1 + nums.length];
        }
    }

    // Just improve the index problem.
    private static class DP_Method2 implements Method {

        @Override
        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int[][] dp = new int[nums.length + 1][nums.length + 1];
            for (int len = 1; len <= nums.length; len++) {
                for (int start = 0, end; (end = start + len) <= nums.length; start++) {
                    for (int pick = start; pick < end; pick++) {
                        int startMarginValue = start - 1 < 0 ? 1 : nums[start - 1];
                        int endMarginValue = end >= nums.length ? 1 : nums[end];
                        int pickValue = startMarginValue * nums[pick] * endMarginValue;
                        int sum = dp[start][pick] + pickValue + dp[pick + 1][end];
                        dp[start][end] = Math.max(dp[start][end], sum);
                    }
                }
            }
            return dp[0][nums.length];
        }
    }

    private static class DFS_Memerization_Method implements Method {

        @Override
        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int[][] dp = new int[nums.length + 2][nums.length + 3];
            int[] arr = new int[nums.length + 2];
            System.arraycopy(nums, 0, arr, 1, nums.length);
            arr[0] = arr[arr.length - 1] = 1;
            return search(arr, dp, 1, arr.length - 1);
        }

        public int search(int[] arr, int[][] dp, int start, int end) {
            if (start == end || dp[start][end] != 0) {
                return dp[start][end];
            }

            for (int pick = start; pick < end; pick++) {
                int pickValue = arr[start - 1] * arr[pick] * arr[end];
                int leftValue = search(arr, dp, start, pick);
                int rightValue = search(arr, dp, pick + 1, end);
                dp[start][end] = Math.max(dp[start][end], leftValue + pickValue + rightValue);
            }

            return dp[start][end];
        }
    }

    private static Method getMethod() {
        return new DP_Method();
    }

    @Test
    public void testcase1() {
        Method method = getMethod();
        int[] nums = {3, 1, 5, 8};
        int actual = method.maxCoins(nums);
        Assert.assertEquals(167, actual);
    }

    @Test
    public void testcase2() {
        Method method = getMethod();
        int[] nums = {2, 3, 7, 9, 1};
        int actual = method.maxCoins(nums);
        Assert.assertEquals(279, actual);
    }
}