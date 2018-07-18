package jiuzhang.dp.backpack;

//BackPackIII contains repeated object
public class BackPackII {
	//Count Max value can be stored in m capacity with repeated use
	public int backPackII(int m, int[] A, int V[]) {
		if (A == null || V == null) {
			return -1;
		}
		int len = A.length;
		if (len == 0 || V.length != len) {
			return -1;
		}
		
		int[] dp = new int[m + 1];
		for (int i = 0; i < len; i++) {
			for (int j = m; j >= A[i]; j--) {
				dp[j] = Math.max(dp[j - 1], dp[j - A[i]] + V[i]);
			}
		}
		return dp[m];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
