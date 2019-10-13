package algorithm.leetcode.p101to150;

public class LeetCode121BestTimeToBuyAndSellStock {

    private interface Method {
        int maxProfit(int[] prices);
    }

    private static final class Normal implements Method {

        public int maxProfit(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int lowest = prices[0];
            int res = 0;
            for (int price : prices) {
                lowest = Math.min(lowest, price);
                res = Math.max(res, price - lowest);
            }
            return res;
        }
    }
}