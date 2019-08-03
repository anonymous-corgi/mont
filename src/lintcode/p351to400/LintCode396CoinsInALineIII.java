package lintcode.p351to400;

/**
 * LintCode 396. Coins in a Line III
 *
 * TThere are n coins with different value in a line.
 * Two players take turns to take a coin from one of the ends of the line until there are no more coins left.
 * The player with the larger amount of money wins.
 *
 * Could you please decide the first player will win or lose?
 *
 * Example
 * Given array A = [3,2,2], return true.
 * Given array A = [1,2,4], return true.
 * Given array A = [1,20,4], return false.
 *
 * Challenge
 * Follow Up Question:
 * If n is even. Is there any hacky algorithm that can decide whether first player will win or lose
 * in O(1) memory and O(n) time?
 */
public class LintCode396CoinsInALineIII {

    private interface Method {
        boolean firstWillWin(int[] values);
    }

    private static class DP_Method implements Method {

        @Override
        public boolean firstWillWin(int[] values) {
            if (values == null || values.length == 0) {
                return false;
            }
            int len = values.length;
            int[][] dp = new int[len][len];
            for (int i = 1; i < len; i++) {
                dp[i][i] = values[i];
            }

            for (int g = 1; g < len; g++) {
                for (int i = 0, iLast = len - g; i < iLast; i++) {
                    int j = i + g;
                    dp[i][j] = Math.max(values[i] - dp[i + 1][j], values[j] - dp[i][j - 1]);
                }
            }

            return dp[0][len - 1] >= 0;
        }
    }
}
