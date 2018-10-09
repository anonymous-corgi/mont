package jiuzhang.dp.coinschange;

public class LeetCode322CoinChange {
	
	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0) {
			return -1;
		}
		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			dp[i] = -1;
			for (int j = 0, cLen = coins.length; j < cLen; j++) {
				if (i >= coins[j] && dp[i - coins[j]] != -1) {
					if (dp[i] == -1 || dp[i - coins[j]] + 1 < dp[i]) {
						dp[i] = dp[i - coins[j]] + 1;
					}
				}
			}
		}
		return dp[amount];
	}

	public static void main(String[] args) {

	}

}
