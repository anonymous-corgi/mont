package com.anonymouscorgi.karakoram.jiuzhang.dp;

/**
 * Given an integer array nums, find a subarray that has the largest product, and return the
 * product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4] Output: 6 Explanation: [2,3] has the largest product 6. Example 2:
 *
 * Input: nums = [-2,0,-1] Output: 0 Explanation: The result cannot be 2, because [-2,-1] is not a
 * subarray. Constraints:
 *
 * 1 <= nums.length <= 2 * 104 -10 <= nums[i] <= 10 The product of any subarray of nums is
 * guaranteed to fit in a 32-bit integer.
 */
interface LeetCode152MaximumProductSubarray {

  interface Algorithm {

    int maxProduct(int[] nums);
  }

  class Normal implements Algorithm {

    @Override
    public int maxProduct(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int max = nums[0];
      int min = nums[0];
      int result = nums[0];
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] < 0) {
          int temp = max;
          max = min;
          min = temp;
        }
        max = Math.max(nums[i], nums[i] * max);
        min = Math.min(nums[i], nums[i] * min);
        result = Math.max(result, max);
      }
      return result;
    }
  }
}
