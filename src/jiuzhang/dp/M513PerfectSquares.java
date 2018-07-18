package Jiuzhang.DP;

import java.util.Arrays;

public class M513PerfectSquares {
	
    public int numSquares(int n) {
        // Write your code here
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; ++i) {
            dp[i * i] = 1;
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M513PerfectSquares one = new M513PerfectSquares();
		int num = 15;
		one.numSquares(num);
	}

}
