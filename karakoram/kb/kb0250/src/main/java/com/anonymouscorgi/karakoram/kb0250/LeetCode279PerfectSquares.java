package com.anonymouscorgi.karakoram.kb0250;

import java.util.Arrays;

/**
 * LeetCode 279. Perfect Squares Medium
 * <p>
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4,
 * 9, 16, ...) which sum to n.
 * <p>
 * Example 1: Input: n = 12 Output: 3 Explanation: 12 = 4 + 4 + 4.
 * <p>
 * Example 2: Input: n = 13 Output: 2 Explanation: 13 = 4 + 9.
 */
interface LeetCode279PerfectSquares {

  int numSquares(int n);

  LeetCode279PerfectSquares SubmittedMethod1 = new LeetCode279PerfectSquares() {

    @Override
    public int numSquares(int n) {
      if (n < 1) {
        return 0;
      }
      int sourceLen = (int) Math.sqrt(n) + 1;
      int[] minNum = new int[n + 1];
      Arrays.setAll(minNum, i -> i);

      for (int i = 1; i < sourceLen; i++) {
        int square = i * i;
        for (int j = square; j <= n; j++) {
          minNum[j] = Math.min(minNum[j], minNum[j - square] + 1);
        }
      }
      return minNum[n];
    }
  };
}
