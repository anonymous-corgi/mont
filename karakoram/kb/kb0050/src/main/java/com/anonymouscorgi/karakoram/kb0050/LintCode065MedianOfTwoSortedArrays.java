package com.anonymouscorgi.karakoram.kb0050;

/**
 * LintCode 065 · Median of two Sorted Arrays
 *
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two
 * sorted arrays.The overall run time complexity should be O(log(m+n)).
 */
interface LintCode065MedianOfTwoSortedArrays {

  double findMedianSortedArrays(int[] a, int[] b);

  // Accepted
  LintCode065MedianOfTwoSortedArrays LOG = new LintCode065MedianOfTwoSortedArrays() {

    @Override
    public double findMedianSortedArrays(int[] a, int[] b) {
      int len = a.length + b.length;
      if (len % 2 == 1) {
        return findKSmallestInSortedArrays(a, b, len / 2 + 1);
      } else {
        return (findKSmallestInSortedArrays(a, b, len / 2) + findKSmallestInSortedArrays(a, b, len / 2 + 1)) / 2d;
      }
    }

    private double findKSmallestInSortedArrays(int[] a, int[] b, int k) {
      int indexStartA = 0;
      int lenA = a.length;
      int indexStartB = 0;
      int lenB = b.length;
      int numberToRemove = k - 1;

      while (numberToRemove > 0) {
        int removeCount = (numberToRemove + 1) / 2 ;
        if (removeCount > lenA) {
          indexStartB += removeCount;
          lenB -= removeCount;
        } else if (removeCount > lenB) {
          indexStartA += removeCount;
          lenA -= removeCount;
        } else {
          if (a[indexStartA + removeCount - 1] > b[indexStartB + removeCount - 1]) {
            indexStartB += removeCount;
            lenB -= removeCount;
          } else {
            indexStartA += removeCount;
            lenA -= removeCount;
          }
        }

        numberToRemove -= removeCount;
      }

      if (lenA == 0) {
        return b[indexStartB];
      } else if (lenB == 0) {
        return a[indexStartA];
      } else {
        return Math.min(a[indexStartA], b[indexStartB]);
      }
    }
  };

  LintCode065MedianOfTwoSortedArrays LINEAR = new LintCode065MedianOfTwoSortedArrays() {
    @Override
    public double findMedianSortedArrays(int[] A, int[] B) {
      if (A == null || B == null) {
        return -1;
      }
      double result;
      int size = A.length + B.length;
      double median1 = 0;
      double median2 = 0;

      int indexA = 0;
      int indexB = 0;
      int index = 1;
      while (index < size + 1 && index <= (size + 1) / 2 + 1) {
        int current;
        if (indexB > B.length - 1 || (indexA < A.length && A[indexA] < B[indexB])) {
          current = A[indexA++];
        } else {
          current = B[indexB++];
        }
        if (index == (size + 1) / 2) {
          median1 = (double) current;
        }
        if (index == (size + 1) / 2 + 1) {
          median2 = (double) current;
        }
        index++;
      }
      if (size % 2 == 1) {
        result = median1;
      } else {
        result = (median1 + median2) / 2;
      }
      return result;
    }
  };
}
