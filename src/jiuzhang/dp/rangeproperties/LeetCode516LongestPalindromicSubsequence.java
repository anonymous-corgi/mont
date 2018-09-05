package jiuzhang.dp.rangeproperties;

public class LeetCode516LongestPalindromicSubsequence {
  
  public static class DP_Improved_method {
    
    public int longestPalindromeSubseq(String s) {
      if (s == null || s.length() == 0) {
        return 0;
      }
      int[][] dp = new int[s.length()][s.length()];
      
      for (int i = s.length() - 1; i >= 0; i--) {
        dp[i][i] = 1;
        for (int j = i + 1; j < s.length(); j++) {
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i + 1][j - 1] + 2;
          } else {
            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
          }
        }
      }
      return dp[0][s.length() - 1];
    }
    
  }
  
  
  public static class DP_method {
    
    public int longestPalindromeSubseq(String s) {
      if (s == null || s.length() == 0) {
        return 0;
      }
      int max = 1;
      int sLen = s.length();
      int[][] dp = new int[sLen][sLen];
      for (int i = 0; i < sLen; i++) {
        dp[i][i] = 1;
      }
      for (int i = 0; i < sLen - 1; i++) {
        dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        max = Math.max(max, dp[i][i + 1]);
      }
      for (int g = 2; g < sLen; g++) {
        for (int i = 0, end = sLen - g; i < end; i++) {
          int e = i + g;
          dp[i][e] = Math.max(dp[i + 1][e], dp[i][e - 1]);
          if (s.charAt(i) == s.charAt(e)) {
            dp[i][e] = Math.max(dp[i][e], dp[i + 1][e - 1] + 2);
          }
          max = Math.max(max, dp[i][e]);
        }
      }
      return max;
    }
    
  }
  
  public static void main(String[] args) {
    
  }
  
}
