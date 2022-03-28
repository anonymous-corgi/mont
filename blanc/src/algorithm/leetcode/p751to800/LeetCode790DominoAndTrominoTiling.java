package algorithm.leetcode.p751to800;

public class LeetCode790DominoAndTrominoTiling {
  
  public int numTilings(int N) {
    final int M = 1000000007;
    long[] dp = new long[N < 3 ? 3 : N + 1];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= N; i++) {
      dp[i] = 2 * dp[i - 1] + dp[i - 3];
      dp[i] %= M;
    }
    return (int) dp[N];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
