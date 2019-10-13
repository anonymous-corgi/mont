package algorithm.lintcode.p351to400;

/**
 * LintCode 395. Coins in a Line II
 * <p>
 * There are n coins with different value in a line.
 * Two players take turns to take one or two coins from left side until there are no more coins left.
 * The player who take the coins with the most value wins.
 * <p>
 * Could you please decide the first player will win or lose?
 * <p>
 * If the first player wins, return true, otherwise return false.
 * <p>
 * Have you met this question in a real algorithm.interview?
 * <p>
 * Example
 * Example 1:
 * Input: [1, 2, 2]
 * Output: true
 * Explanation: The first player takes 2 coins.
 * <p>
 * Example 2:
 * Input: [1, 2, 4]
 * Output: false
 * Explanation: Whether the first player takes 1 coin or 2, the second player will gain more value.
 */
public class LintCode395CoinsInALineII {

    public boolean firstWillWin(int[] values) {
        if (values.length < 3) {
            return values.length != 0;
        }
        int[] dp = new int[values.length];
        dp[dp.length - 1] = values[dp.length - 1];
        dp[dp.length - 2] = values[dp.length - 2] + values[dp.length - 1];
        for (int i = dp.length - 3; i >= 0; i--) {
            dp[i] = Math.max(values[i] - dp[i + 1], values[i] + values[i + 1] - dp[i + 2]);
        }
        return dp[0] > 0;
    }
}
