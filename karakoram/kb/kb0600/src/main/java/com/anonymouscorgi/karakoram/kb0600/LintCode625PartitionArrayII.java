package com.anonymouscorgi.karakoram.kb0600;

/**
 * LintCode 625. Partition Array II
 *
 * <p>Description
 *
 * <p>Partition an unsorted integer array into three parts: The front part < low; The middle part >=
 * low & <= high; The tail part > high. Return any of the possible solutions.
 *
 * <p>Example 1: Input: [4,3,4,1,2,3,1,2] 2 3 Output: [1,1,2,3,2,3,4,4] Explanation:
 * [1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not Example 2: Input:
 * [3,2,1] 2 3 Output: [1,2,3]
 */
interface LintCode625PartitionArrayII {

  void partition2(int[] nums, int low, int high);

  LintCode625PartitionArrayII QUICK_SORT =
      new LintCode625PartitionArrayII() {

        @Override
        public void partition2(int[] nums, int low, int high) {
          // write your code here
          if (nums == null || nums.length == 0) {
            return;
          }
          int cursor = 0;
          int lowNumIndex = 0;
          int highNumIndex = nums.length - 1;
          while (cursor <= highNumIndex) {
            if (nums[cursor] < low) {
              swap(nums, cursor, lowNumIndex);
              cursor++;
              lowNumIndex++;
            } else if (nums[cursor] > high) {
              swap(nums, cursor, highNumIndex);
              highNumIndex--;
            } else {
              cursor++;
            }
          }
        }

        private void swap(int[] nums, int a, int b) {
          if (a == b) {
            return;
          }
          int temp = nums[a];
          nums[a] = nums[b];
          nums[b] = temp;
        }
      };
}
