package com.anonymouscorgi.karakoram.kb0450;

import com.anonymouscorgi.karakoram.annotation.Medium;

/**
 * LintCode 461. Kth Smallest Numbers in Unsorted Array
 *
 * <p>Find the kth smallest numbers in an unsorted integer array.
 */
@Medium
interface LintCode461KthSmallestNumberInUnsortedArray {

  int kthSmallest(int k, int[] nums);

  LintCode461KthSmallestNumberInUnsortedArray PARTITION =
      new LintCode461KthSmallestNumberInUnsortedArray() {

        @Override
        public int kthSmallest(int k, int[] nums) {
          return quickSelect(nums, 0, nums.length - 1, k - 1);
        }

        private int quickSelect(int[] nums, int start, int end, int targetIndex) {
          int pivotValue = nums[end];
          int lowerPivotIndex = start - 1;
          int upperPivotIndex = end + 1;
          int current = start;
          while (current < upperPivotIndex) {
            if (nums[current] < pivotValue) {
              swap(nums, ++lowerPivotIndex, current++);
            } else if (nums[current] > pivotValue) {
              swap(nums, current, --upperPivotIndex);
            } else {
              current++;
            }
          }

          if (targetIndex <= lowerPivotIndex) {
            return quickSelect(nums, start, lowerPivotIndex, targetIndex);
          } else if (targetIndex >= upperPivotIndex) {
            return quickSelect(nums, upperPivotIndex, end, targetIndex);
          } else {
            return nums[targetIndex];
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
