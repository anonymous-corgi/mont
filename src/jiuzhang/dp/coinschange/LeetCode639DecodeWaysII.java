package jiuzhang.dp.coinschange;

public class LeetCode639DecodeWaysII {
  
  private final long MOD = (long) (1E9 + 7);
  
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] cs = s.toCharArray();
    int len = s.length();
    long[] dp = new long[len + 1];
    dp[0] = 1;
    dp[1] = cs[len - 1] == '0' ? 0 : (cs[len - 1] == '*' ? 9 : 1);
    for (int i = 2; i <= len; i++) {
      char cur = cs[len - i];
      char pre = cs[len - i + 1];
      if (cur == '0') {
        continue;
      }
      if (cur == '*') {
        if (pre == '*') {
          dp[i] += dp[i - 2] * 15;
        } else if (pre < '7') {
          dp[i] += dp[i - 2] * 2;
        } else {
          dp[i] += dp[i - 2];
        }
        dp[i] += dp[i - 1] * 9;
      } else if (cur == '1') {
        if (pre == '*') {
          dp[i] += dp[i - 2] * 9;
        } else {
          dp[i] += dp[i - 2];
        }
        dp[i] += dp[i - 1];
      } else if (cur == '2') {
        if (pre == '*') {
          dp[i] += dp[i - 2] * 6l;
        } else if (pre < '7') {
          dp[i] += dp[i - 2];
        }
        dp[i] += dp[i - 1];
      } else {
        dp[i] += dp[i - 1];
      }
      
      if (dp[i] > MOD) {
        dp[i] %=  MOD;      
      }

    }
    return (int) dp[len];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode639DecodeWaysII one = new LeetCode639DecodeWaysII();
    String s = "1*";
    System.out.println(one.numDecodings(s));
  }

}
