package algorithm.lintcode.p351to400;

/**
 * LintCode 394. Coins in a Line I
 * Medium
 * <p>
 * There are n coins in a line.
 * Two players take turns to take one or two coins from right side until there are no more coins left.
 * The player who take the last coin wins.
 * <p>
 * Could you please decide the first player will win or lose?
 * <p>
 * If the first player wins, return true, otherwise return false.
 * Have you met this question in a real algorithm.interview?  Yes
 * Problem Correction
 * <p>
 * Example
 * Example 1:
 * Input: 1
 * Output: true
 * <p>
 * Example 2:
 * Input: 4
 * Output: true
 * <p>
 * Explanation:
 * The first player takes 1 coin at first. Then there are 3 coins left.
 * Whether the second player takes 1 coin or two, then the first player can take all coin(s) left.
 */
public class LintCode394CoinsInALineI {

    private interface Method {
        boolean firstWillWin(int n);
    }

    private static class Cheating_Method implements Method {

        @Override
        public boolean firstWillWin(int n) {
            return n % 3 != 0;
        }
    }

    private static class DP_Method implements Method {

        @Override
        public boolean firstWillWin(int n) {
            if (n < 3) {
                return n != 0;
            }
            boolean[] dp = new boolean[n];
            dp[0] = true;
            dp[1] = true;
            for (int i = 2; i < n; i++) {
                dp[i] = !(dp[i - 1] && dp[i - 2]);
            }
            return dp[n - 1];
        }
    }
}
