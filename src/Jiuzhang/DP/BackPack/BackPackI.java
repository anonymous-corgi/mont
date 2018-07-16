package Jiuzhang.DP.BackPack;

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
	
	public int backPack(int m, int[] A) {
		if (A == null) {
			return 0;
		}
		int aLen = A.length;
		if (aLen == 0) {
			return 0;
		}
		int[] dp = new int[m + 1];
		for (int i = 0; i < aLen; i++) {
			for (int j = m; j >= A[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - A[i]] + A[i]);
			}
		}
		return dp[m];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
