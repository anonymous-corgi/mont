package com.anonymouscorgi.karakoram.kb0200;

import com.anonymouscorgi.karakoram.annotation.Accepted;
import com.anonymouscorgi.karakoram.annotation.Medium;

/**
 * LeetCode 215. Kth Largest Element in an Array
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 *
 *
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2 Output: 5 Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4 Output: 4
 * <p>
 *
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105 -104 <= nums[i] <= 104
 */
@Medium
interface LeetCode215KthLargestElementInAnArray {

  int findKthLargest(int[] nums, int k);

  @Accepted
  LeetCode215KthLargestElementInAnArray Method = new LeetCode215KthLargestElementInAnArray() {
    @Override
    public int findKthLargest(int[] nums, int k) {
      return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    private int quickSelect(int[] nums, int start, int end, int targetIndex) {
      if (start == end) {
        return nums[start];
      }

      // Partition the array and get the pivot index
      int pivotValue = nums[end]; // Use the last element as the pivot
      int lowerPivotIndex = start - 1;       // Boundary for elements greater than pivot
      int upperPivotIndex = end + 1;            // Boundary for elements equal to pivot
      int current = start;

      while (current < upperPivotIndex) {
        if (nums[current] > pivotValue) {
          swap(nums, ++lowerPivotIndex, current++);
        } else if (nums[current] < pivotValue) {
          swap(nums, --upperPivotIndex, current);
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
