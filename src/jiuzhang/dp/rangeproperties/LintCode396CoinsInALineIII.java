package jiuzhang.dp.rangeproperties;

public class LintCode396CoinsInALineIII {
  
  public boolean firstWillWin(int[] values) {
    // write your code here
    if (values == null || values.length == 0) {
      return false;
    }
    int len = values.length;
    int[][] dp = new int[len][len];
    for (int i = 1; i < len; i++) {
      dp[i][i] = values[i];
    }
    
    for (int g = 1; g < len; g++) {
      for (int i = 0, iLast = len - g; i < iLast; i++) {
        int j = i + g;
        dp[i][j] = Math.max(values[i] - dp[i + 1][j], values[j] - dp[i][j - 1]);
      }
    }
    
    return dp[0][len - 1] >= 0;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
