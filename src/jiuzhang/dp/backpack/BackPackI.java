package jiuzhang.dp.backpack;

/**
 * LintCode 92 BackPack
 * https://www.lintcode.com/problem/backpack/description
 * 
 * Description
 * Given n items with size Ai, an integer m denotes the size of a backpack. 
 * How full you can fill this backpack?
 *
 * Example
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, 
 * we can select [2, 3, 5], so that the max size we can fill this backpack is 10.
 * If the backpack size is 12. we can select [2, 3, 7] 
 * so that we can fulfill the backpack.
 * 
 * You function should return the max size we can fill in the given backpack.
 */
public class BackPackI {

	interface  Method {
		int backPack(int capacity, int[] weights);
	}

	//Count Max weight can be stored in m capacity without repeated use
	//This is the optimal way to count the maximum capacity
	public static class Method1 implements Method {
		@Override
		public int backPack(int capacity, int[] weights) {
			if (weights == null || weights.length == 0) {
				return 0;
			}
			int[] dp = new int[capacity + 1];
			for (int i = 0; i < weights.length; i++) {
				for (int j = capacity; j >= weights[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - weights[i]] + weights[i]);
				}
			}
			return dp[capacity];
		}
	}

	//This solution is mostly used for count number of possibilities.
	//Similar to BackPackVI
	public static class Method2 implements Method {
		@Override
		public int backPack(int capacity, int[] weights) {
			boolean f[][] = new boolean[weights.length + 1][capacity + 1];
			f[0][0] = true;
			for (int i = 1; i <= weights.length; i++) {
				for (int j = 0; j <= capacity; j++) {
					f[i][j] = f[i - 1][j];
					if (j >= weights[i-1] && f[i-1][j - weights[i-1]]) {
						f[i][j] = true;
					}
				} // for j
			} // for i
			for (int i = capacity; i >= 0; i--) {
				if (f[weights.length][i]) {
					return i;
				}
			}
			return 0;
		}
	}

}
