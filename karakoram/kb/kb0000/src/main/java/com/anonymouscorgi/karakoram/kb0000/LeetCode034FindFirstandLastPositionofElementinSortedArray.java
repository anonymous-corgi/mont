package com.anonymouscorgi.karakoram.kb0000;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending
 * position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 * Example 1: Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4]
 *
 * Example 2: Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
 *
 * Example 3: Input: nums = [], target = 0 Output: [-1,-1]
 *
 *
 * Constraints:
 *
 *
 * 0 <= nums.length <= 10⁵ -10⁹ <= nums[i] <= 10⁹ nums is a non-decreasing array. -10⁹ <= target <=
 * 10⁹
 */
interface LeetCode034FindFirstandLastPositionofElementinSortedArray {

  int[] searchRange(int[] nums, int target);

  LeetCode034FindFirstandLastPositionofElementinSortedArray Method =
      new LeetCode034FindFirstandLastPositionofElementinSortedArray() {

        @Override
        public int[] searchRange(int[] nums, int target) {
          if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
          }

          int[] res = {-1, -1};
          int start = 0;
          int end = nums.length - 1;
          while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
              start = mid + 1;
            } else {
              end = mid;
            }
          }

          if (nums[end] != target) {
            return res;
          }
          res[0] = end;

          end = nums.length - 1;
          while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] > target) {
              end = mid - 1;
            } else {
              start = mid;
            }
          }
          res[1] = start;
          return res;
        }
      };
}
