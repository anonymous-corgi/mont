package com.anonymouscorgi.karakoram.kb0300;

/**
 * LeetCode 322. Coin Change
 *
 * You are given coins of different denominations and a total amount of money amount. Write a
 * function to compute the fewest number of coins that you need to make up that amount. If that
 * amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1: Input: coins = [1, 2, 5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
 *
 * Example 2: Input: coins = [2], amount = 3 Output: -1
 */
interface LeetCode322CoinChange {

  int coinChange(int[] coins, int amount);

  LeetCode322CoinChange Method = new LeetCode322CoinChange() {

    @Override
    public int coinChange(int[] coins, int amount) {
      if (coins == null || coins.length == 0) {
        return -1;
      }
      int[] dp = new int[amount + 1];
      for (int i = 1; i < dp.length; i++) {
        dp[i] = -1;
        for (int coin : coins) {
          if (i < coin) {
            continue;
          }
          if (dp[i - coin] != -1 && (dp[i] == -1 || dp[i - coin] + 1 < dp[i])) {
            dp[i] = dp[i - coin] + 1;
          }
        }
      }
      return dp[amount];
    }
  };
}
