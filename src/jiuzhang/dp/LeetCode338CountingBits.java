package jiuzhang.dp;

public class LeetCode338CountingBits {
  
  public int[] countBits(int num) {
    int[] dp = new int[num + 1];
    for (int i = 0; i <= num; i++) {
      dp[i] = dp[i >> 1] + (i & 1); 
    }
    return dp;
  }
  
  public int[] countBits2(int num) {
    int[][] dp = new int[2][num + 1];
    for (int i = 1; i <= num; i++) {
      dp[1][i] = (i & (i - 1));
      dp[0][i] = dp[0][dp[1][i]] + 1;
    }
    return dp[0];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode338CountingBits one = new LeetCode338CountingBits();
    int num = 10;
    System.out.println(one.countBits2(num));
  }

}
