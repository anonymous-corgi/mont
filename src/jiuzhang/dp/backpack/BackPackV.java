package jiuzhang.dp.backpack;

public class BackPackV {

	//Count How many ways can get to target without repeated use
	public int backPackV(int[] weights, int capacity) {
		if (weights == null || weights.length == 0) {
			return 0;
		}
		int[] dp = new int[capacity + 1];
		dp[0] = 1;
		for (int i = 0; i < weights.length; i++) {
			//If j starts from weights[i] might cause duplication
			for (int j = capacity; j >= weights[i]; j--) {
				dp[j] += dp[j - weights[i]];
			}
		}
		return dp[capacity];
	}

}
