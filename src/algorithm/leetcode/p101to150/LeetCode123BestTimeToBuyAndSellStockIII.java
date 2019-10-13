package algorithm.leetcode.p101to150;

public class LeetCode123BestTimeToBuyAndSellStockIII {

    private interface Method {
        int maxProfit(int[] prices);
    }

    private static final class DP1 implements Method {

        public int maxProfit(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }
            int[][] dp = new int[3][prices.length + 1];
            for (int i = 1; i <= 2; i++) {
                int prevMax = Integer.MIN_VALUE;
                for (int j = 2; j <= prices.length; j++) {
                    prevMax = Math.max(prevMax, dp[i - 1][j - 2] - prices[j - 2]);
                    dp[i][j] = Math.max(dp[i][j - 1], prices[j - 1] + prevMax);
                }
            }
            return dp[2][prices.length];
        }
    }

    /**
     * This solution is similar to {@link algorithm.leetcode.p401to450.LeetCode410SplitArrayLargestSum}
     * But this takes much longer time.
     */
    private static final class DP2 implements Method {

        public int maxProfit(int[] prices) {
            int[] dp = new int[prices.length + 1];
            for (int t = 1; t <= 2; t++) {
                for (int d = prices.length; d > 0; d--) {
                    int highest = 0;
                    for (int l = d; l > 0; l--) {
                        highest = Math.max(highest, prices[l - 1]);
                        dp[d] = Math.max(dp[d], highest - prices[l - 1] + dp[l - 1]);
                    }
                }
            }
            return dp[prices.length];
        }
    }
}