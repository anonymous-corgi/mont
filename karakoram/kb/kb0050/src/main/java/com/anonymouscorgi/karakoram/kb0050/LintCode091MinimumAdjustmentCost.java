package com.anonymouscorgi.karakoram.kb0050;

import java.util.Arrays;
import java.util.List;

/**
 * LintCode 091. Minimum Adjustment Cost
 *
 * Description Given an integer array, adjust each integers so that the difference of every adjacent
 * integers are not greater than a given number target.
 *
 * If the array before adjustment is A, the array after adjustment is B, you should minimize the sum
 * of |A[i]-B[i]|
 *
 * You can assume each number in the array is a positive integer and not greater than 100.
 *
 * Example Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is
 * 2 and it’s minimal.
 *
 * Return 2.
 */
interface LintCode091MinimumAdjustmentCost {

  int MinAdjustmentCost(List<Integer> A, int target);

  LintCode091MinimumAdjustmentCost Method = new LintCode091MinimumAdjustmentCost() {
    @Override
    public int MinAdjustmentCost(List<Integer> A, int target) {
      if (A == null || A.size() == 0) {
        return 0;
      }
      int[][] dp = new int[A.size()][101];
      for (int j = 1; j <= 100; j++) {
        dp[0][j] = Math.abs(j - A.get(0));
      }
      for (int i = 1; i < A.size(); i++) {
        Arrays.fill(dp[i], Integer.MAX_VALUE);
      }

      for (int i = 1; i < A.size(); i++) {
        for (int j = 0; j <= 100; j++) {
          // Calculate the range of possible values for the current number based on the target
          int kStart = Math.max(1, j - target);
          int kEnd = Math.min(j + target, 100);
          int diffCost = Math.abs(j - A.get(i));
          for (int k = kStart; k <= kEnd; k++) {
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + diffCost);
          }
        }
      }

      int res = Integer.MAX_VALUE;
      for (int i = 1; i <= 100; i++) {
        res = Math.min(res, dp[A.size() - 1][i]);
      }
      return res;
    }
  };

  LintCode091MinimumAdjustmentCost Original = new LintCode091MinimumAdjustmentCost() {
    @Override
    public int MinAdjustmentCost(List<Integer> A, int target) {
      // write your code here
      if (A == null || A.size() == 0) {
        return 0;
      }
      int aLen = A.size() + 1;
      int[][] dp = new int[aLen][101];
      for (int i = 1; i < aLen; i++) {
        for (int j = 0; j < 101; j++) {
          dp[i][j] = Integer.MAX_VALUE;
        }
      }
      for (int i = 1; i < aLen; i++) {
        for (int j = 0; j < 101; j++) {
          if (dp[i - 1][j] != Integer.MAX_VALUE) {
            int kStart = j - target;
            int kEnd = j + target;
            kStart = kStart < 0 ? 0 : kStart;
            kEnd = kEnd > 100 ? 100 : kEnd;
            for (int k = kStart; k <= kEnd; k++) {
              int diff = dp[i - 1][j] + Math.abs(k - A.get(i - 1));
              if (dp[i][k] > diff) {
                dp[i][k] = diff;
              }
            }
          }
        }
      }
      int res = Integer.MAX_VALUE;
      aLen--;
      for (int i = 0; i < 101; i++) {
        res = Math.min(res, dp[aLen][i]);
      }
      return res;
    }
  };
}
