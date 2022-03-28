package algorithm.leetcode.p201to250;

/**
 * LeetCode 213. House Robber II
 * Medium
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 */
public class LeetCode213HouseRobberII {

    private interface Method {
        int rob(int[] nums);
    }

    private static class DP_method implements Method {

        @Override
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int[] zero = new int[nums.length + 1];
            int[] one = new int[nums.length + 1];
            zero[1] = nums[0];
            one[2] = nums[1];
            for (int i = 2; i < nums.length; i++) {
                zero[i] = Math.max(zero[i - 1], nums[i - 1] + zero[i - 2]);
            }
            for (int i = 3; i <= nums.length; i++) {
                one[i] = Math.max(one[i - 1], nums[i - 1] + one[i - 2]);
            }
            return Math.max(zero[nums.length - 1], one[nums.length]);
        }

    }

    private static class DP_Improved_method implements Method {

        @Override
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
        }

        private int rob(int[] nums, int start, int end) {
            int minus1 = 0;
            int minus2 = 0;
            for (int i = start; i < end; i++) {
                int temp = minus1;
                minus1 = Math.max(minus1, nums[i] + minus2);
                minus2 = temp;
            }
            return minus1;
        }
    }
}
