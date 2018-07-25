package jiuzhang.dp.twoarrays;

public class LintCode077LongestCommonSubsequence {
  
  public int longestCommonSubsequence(String A, String B) {
    // write your code here
    if (A == null || B == null) {
      return 0;
    }
    if (A.length() == 0 || B.length() == 0) {
      return 0;
    }
    int aLen = A.length();
    int bLen = B.length();
    int[][] f = new int[aLen + 1][bLen + 1];
    for (int i = 1; i <= aLen; i++) {
      for (int j = 1; j <= bLen; j++) {
        f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          f[i][j] = f[i - 1][j - 1] + 1;
        }
      }
    }
    return f[aLen][bLen];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
