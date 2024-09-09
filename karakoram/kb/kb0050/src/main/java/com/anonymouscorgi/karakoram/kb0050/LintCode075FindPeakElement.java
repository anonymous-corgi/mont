package com.anonymouscorgi.karakoram.kb0050;

interface LintCode075FindPeakElement {

  int findPeak(int[] nums);

  // Accepted
  LintCode075FindPeakElement Method = new LintCode075FindPeakElement() {
    @Override
    public int findPeak(int[] nums) {
      if (nums == null || nums.length == 0) {
        return -1;
      }

      int start = 0;
      int end = nums.length - 1;
      while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] > nums[mid + 1]) {
          end = mid;
        } else {
          start = mid + 1;
        }
      }
      return start;
    }
  };

  // Accepted. For case that must have a peak, no corner case.
  LintCode075FindPeakElement ReCursive_Method = new LintCode075FindPeakElement() {

    @Override
    public int findPeak(int[] nums) {
      return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int start, int end) {
      if (start + 2 > end) {
        return -1;
      }

      int mid = start + (end - start) / 2;
      if (nums[mid - 1] > nums[mid]) {
        return findPeak(nums, start, mid);
      } else if (nums[mid] < nums[mid + 1]) {
        return findPeak(nums, mid, end);
      } else {
        return mid;
      }
    }
  };
}
