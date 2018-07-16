package Jiuzhang.DP.BackPack;

//BackPackII doesn't contain repeated object
public class BackPackIII {
	
	public int backPackIII(int[] A, int[] V, int m) {
		if (A == null || V == null) {
			return -1;
		}
		int len = A.length;
		if (len == 0 || V.length != len) {
			return -1;
		}
		
		int[] dp = new int[m + 1];
		for (int i = 0; i < len; i++) {
			for (int j = A[i]; j <= m; j++) {
				dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
			}
		}
		return dp[m];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
