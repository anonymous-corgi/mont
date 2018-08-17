package jiuzhang.dp.twoarrays;

public class LeetCode115DistinctSubsequences {
  
  public int numDistinct(String S, String T) {
    if (S == null || T == null) {
      return 0;
    }
    int sLen = S.length() + 1;
    int tLen = T.length() + 1;
    int[][] dp = new int[sLen][tLen];
    dp[0][0] = 1;
    for (int i = 1; i < sLen; i++) {
      dp[i][0] = 1;
      for (int j = 1; j < tLen; j++) {
        dp[i][j] = dp[i - 1][j];
        if (S.charAt(i - 1) == T.charAt(j - 1)) {
          dp[i][j] += dp[i - 1][j - 1];
        }
      }
    }
    return dp[sLen - 1][tLen - 1];
  }
  /**
   * In point (4, 2)
   * d[3][2] means "abc" has d[3][2] "ab" not using the second "b"
   * +=d[2][1] means "ab" has d[2][1] "a", so we can used the second pair
   * of "b" to generate d[2][1] "ab"
   */

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode115DistinctSubsequences one = new LeetCode115DistinctSubsequences();
    String S = "abcb";
    String T = "ab";
    System.out.println(one.numDistinct(S, T));
  }

}
