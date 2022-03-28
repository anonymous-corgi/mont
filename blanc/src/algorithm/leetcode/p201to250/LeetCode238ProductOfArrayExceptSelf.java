package algorithm.leetcode.p201to250;

public class LeetCode238ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right *= nums[i + 1];
            result[i] *= right;
        }
        return result;
    }
}