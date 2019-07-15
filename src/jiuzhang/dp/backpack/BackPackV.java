package jiuzhang.dp.backpack;

public class BackPackV {

	//Count How many ways can get to target without repeated use
	private interface Method {
		int backPackV(int[] weights, int capacity);
	}

	private class Method1 implements Method {

		@Override
		public int backPackV(int[] weights, int capacity) {
			if (weights == null || weights.length == 0) {
				return 0;
			}
			int[] dp = new int[capacity + 1];
			dp[0] = 1;
			for (int weight : weights) {
				//If j starts from weights[i] might cause duplication
				for (int j = capacity; j >= weight; j--) {
					dp[j] += dp[j - weight];
				}
			}
			return dp[capacity];
		}
	}
}
