package com.anonymouscorgi.karakoram.kb0050;

/**
 * Description Given n distinct positive integers, integer k (k <= n) and a number target.
 * <p>
 * Find k numbers where sum is target. Calculate how many solutions there are?
 * <p>
 * Have you met this question in a real algorithm.interview?  Yes Example Given [1,2,3,4], k = 2,
 * target = 5.
 * <p>
 * There are 2 solutions: [1,4] and [2,3].
 * <p>
 * Return 2.
 */
interface LintCode089KSum {

  int kSum(int[] A, int k, int target);

  LintCode089KSum Method = new LintCode089KSum() {

    @Override
    public int kSum(int[] A, int k, int target) {
      if (A == null || A.length == 0 || target == 0) {
        return 0;
      }

      int[][] dp = new int[k + 1][target + 1];
      dp[0][0] = 1;
      for (int i = 0; i < A.length; i++) {
        if (A[i] > target) {
          continue;
        }
        int numOfNumberMax = Math.min(i + 1, k);
        for (int numOfNumber = numOfNumberMax; numOfNumber > 0; numOfNumber--) {
          for (int sum = target; sum >= A[i]; sum--) {
            dp[numOfNumber][sum] += dp[numOfNumber - 1][sum - A[i]];
          }
        }
      }
      return dp[k][target];
    }
  };
}
