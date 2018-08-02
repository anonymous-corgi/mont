package leetcode.p251to300;

public class LeetCode276PaintFence {
  
  public int numWays(int n, int k) {
    int[][] dp = new int[2][n + 1];
    dp[0][1] = k;
    for (int i = 2; i <= n; i++) {
      dp[0][i] = (dp[0][i - 1] + dp[1][i - 1]) * (k - 1);
      dp[1][i] = dp[0][i - 1];
    }
    return dp[0][n] + dp[1][n];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
