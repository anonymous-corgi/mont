package leetcode.p101to150;

public class LeetCode122BestTimeToBuyAndSellStockII {

    private interface Method {
        int maxProfit(int[] prices);
    }

    private static final class Impl implements Method {

        public int maxProfit(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }
    }
}