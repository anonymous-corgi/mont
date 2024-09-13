package com.anonymouscorgi.karakoram.kb0450;

interface LintCode457ClassicalBinarySearch {

  int findPosition(int[] nums, int target);

  LintCode457ClassicalBinarySearch Method = new LintCode457ClassicalBinarySearch() {
    @Override
    public int findPosition(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
      int start = 0;
      int end = nums.length - 1;
      while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] > target) {
          end = mid;
        } else if (nums[mid] < target) {
          start = mid + 1;
        } else {
          return mid;
        }
      }
      return nums[start] == target ? start : -1;
    }
  };
}
