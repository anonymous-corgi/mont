package com.anonymouscorgi.karakoram.kb1500;

import com.anonymouscorgi.karakoram.annotation.Accepted;

/**
 * LeetCode 1539. Kth Missing Positive Number
 * <p>
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 */
interface LeetCode1539KthMissingPositiveNumber {

  int findKthPositive(int[] arr, int k);

  @Accepted
  LeetCode1539KthMissingPositiveNumber Method = new LeetCode1539KthMissingPositiveNumber() {
    @Override
    public int findKthPositive(int[] arr, int k) {
      int left = 0;
      int right = arr.length - 1;
      while (left < right) {
        int mid = left + (right - left + 1) / 2;
        if (arr[mid] - (mid + 1) < k) {
          left = mid;
        } else {
          right = mid - 1;
        }
      }
      return (arr[left] - (left + 1) < k) ? (left + 1) + k : k;
    }
  };
}
