package com.anonymouscorgi.karakoram.kb0100;

/**
 * LintCode 148 · Sort Colors
 * <p>
 * Description
 * <p>
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same
 * color are adjacent, with the colors in the order red, white and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
interface LintCode148SortColors {

  void sortColors(int[] nums);

  // Accepted
  LintCode148SortColors QUICK_SORT = new LintCode148SortColors() {

    @Override
    public void sortColors(int[] nums) {
      if (nums == null || nums.length == 0) {
        return;
      }

      int num0Index = 0;
      int num2Index = nums.length - 1;
      int cursor = 0;
      while (cursor <= num2Index) {
        if (nums[cursor] == 0) {
          swap(nums, cursor, num0Index);
          num0Index++;
          cursor++;
        } else if (nums[cursor] == 2) {
          swap(nums, cursor, num2Index);
          num2Index--;
        } else {
          cursor++;
        }
      }
    }

    private void swap(int[] nums, int a, int b) {
      if (a == b) {
        return;
      }
      nums[b] += nums[a];
      nums[a] = nums[b] - nums[a];
      nums[b] = nums[b] - nums[a];
    }
  };
}
