package com.anonymouscorgi.karakoram.kb0450;

/**
 * 458 Last Position of Target
 * <p>
 * Description
 * <p>
 * Find the last position of a target number in a sorted array. Return -1 if target does not exist.
 * <p>
 * Example Given [1, 2, 2, 4, 5, 5].
 * For target = 2, return 2.
 * For target = 5, return 5.
 * For target = 6, return -1.
 */
interface LintCode458LastPositionOfTarget {

  int lastPosition(int[] nums, int target);

  LintCode458LastPositionOfTarget Method = new LintCode458LastPositionOfTarget() {

    @Override
    public int lastPosition(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
      int start = 0;
      int end = nums.length - 1;
      while (start < end) {
        int mid = start + (end - start + 1) / 2;
        if (nums[mid] > target) {
          end = mid - 1;
        } else {
          start = mid;
        }
      }
      return nums[start] == target ? start : -1;
    }
  };
}
