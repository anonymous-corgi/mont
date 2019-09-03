package leetcode.p151to200;

/**
 * LeetCode 188. Best Time to Buy and Sell Stock IV
 * Hard
 *
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
@SuppressWarnings("unused")
public class LeetCode188BestTimeToBuyAndSellStockIV {

    private interface Method {
        int maxProfit(int k, int[] prices);
    }

    private static class DP1 implements Method {

        @Override
        public int maxProfit(int k, int[] prices) {
            if (k >= prices.length / 2) {
                return quickSolve(prices);
            }
            //After the i times of transaction and the last transaction is done in j day,
            //The profit is dp[i][j]
            int[][] dp = new int[k + 1][prices.length + 1];
            for (int i = 1; i <= k; i++) {
                int prevMax = Integer.MIN_VALUE;
                for (int j = 2; j <= prices.length; j++) {
                    //prevMax records the maximum profit minus the cost used to buy stock before day j.
                    prevMax = Math.max(prevMax, dp[i - 1][j - 2] - prices[j - 2]);

                    dp[i][j] = Math.max(dp[i][j - 1], prices[j - 1] + prevMax);
                }
            }
            return dp[k][prices.length];
        }

        private int quickSolve(int[] prices) {
            int len = prices.length, profit = 0;
            for (int i = 1; i < len; i++) {
                // As long as there is a price gap, we gain a profit.
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
    }

    private static class DP2 implements Method {

        @Override
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int len = prices.length;
            int[][] pre = new int[2][len + 1];
            int[][] cur = new int[2][len + 1];
            for (int times = 0; times < k; times++) {
                for (int day = 1; day <= len; day++) {
                    cur[0][day] = 0;
                    // o means has stock
                    if (day != 1) {
                        //(Bought previously in the same stage, Bought today and stage changed)
                        cur[0][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
                                pre[1][day - 1]);
                    } else {
                        //(Bought today)
                        cur[0][day] = Math.max(cur[0][day], pre[1][day - 1]);
                    }
                }
                for (int day = 1; day <= len; day++) {
                    cur[1][day] = 0;
                    if (day != 1) {
                        //(Sold today, Sold previously)
                        cur[1][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
                                cur[1][day - 1]);
                    } else {
                        cur[1][day] = 0;
                    }
                }
                pre = cur;
                cur = pre;
            }
            return pre[1][len];
        }

    }

    private static Method getMethod() {
        return new DP1();
    }

    public static void main(String[] args) {
        int K = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        Method method = getMethod();
        System.out.println(method.maxProfit(K, prices));
    }
}
