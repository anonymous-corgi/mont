package jiuzhang.dp.coinschange;

public class LeetCode091DecodeWays {
  
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] cs = s.toCharArray();
    int len = s.length();
    int[] dp = new int[len + 1];
    dp[0] = 1;
    dp[1] = cs[len - 1] == '0' ? 0 : 1;
    for (int i = 2; i <= len; i++) {
      char prev = cs[len - i + 1];
      char cur = cs[len - i];
      if (cur == '0') {
        continue;
      }
      if (cur == '1' || (cur == '2' && prev < '7')) {
        dp[i] = dp[i - 2];
      }
      dp[i] += dp[i - 1];
    }
    return dp[len];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
