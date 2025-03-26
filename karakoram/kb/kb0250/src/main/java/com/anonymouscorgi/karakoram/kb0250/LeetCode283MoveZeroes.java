package com.anonymouscorgi.karakoram.kb0250;

/**
 * LeetCode 283. Move Zeroes
 * <p>
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order
 * of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * <p>
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0]
 * <p>
 * Output: [0]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * <p>
 * -231 <= nums[i] <= 231 - 1
 */
interface LeetCode283MoveZeroes {

  void moveZeroes(int[] nums);

  LeetCode283MoveZeroes Method = new LeetCode283MoveZeroes() {

    @Override
    public void moveZeroes(int[] nums) {
      int pivotIndex = -1;
      for (int current = 0; current < nums.length; current++) {
        if (nums[current] != 0) {
          swap(nums, ++pivotIndex, current);
        }
      }
    }

    private void swap(int[] nums, int a, int b) {
      if (a != b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
      }
    }
  };
}
