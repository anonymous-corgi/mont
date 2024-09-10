package com.anonymouscorgi.karakoram.kb0000;

/**
 * LintCode 14 · First Position of Target
 * <p>
 * Description
 * <p>
 * Given a sorted array (ascending order) and a target number, find the first index of this number
 * in O(logn) time complexity. If the target number does not exist in the array, return -1.
 */
interface LintCode014FirstPositionOfTarget {

  int binarySearch(int[] nums, int target);

  LintCode014FirstPositionOfTarget Method = new LintCode014FirstPositionOfTarget() {

    @Override
    public int binarySearch(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
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
      return nums[end] == target ? end : -1;
    }
  };

  LintCode014FirstPositionOfTarget Recursive_Method = new LintCode014FirstPositionOfTarget() {

    @Override
    public int binarySearch(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return -1;
      }

      return binarySearch(nums, target, 0, nums.length - 1, -1);
    }

    private static int binarySearch(int[] nums, int target, int head, int tail, int lastResult) {
      if (head > tail) {
        return lastResult;
      }

      int mid = head + (tail - head) / 2;
      if (nums[mid] < target) {
        return binarySearch(nums, target, mid + 1, tail, lastResult);
      } else if (nums[mid] > target) {
        return binarySearch(nums, target, head, mid - 1, lastResult);
      } else {
        return binarySearch(nums, target, head, mid - 1, mid);
      }
    }
  };
}
