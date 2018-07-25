package jiuzhang.dp.rangeproperties;

public class LeetCode087ScrambleString {
  
  public boolean isScramble(String s1, String s2) {
    // write your code here
    if (s1 == null || s2 == null) {
      return false;
    }
    if (s1.length() != s2.length()) {
      return false;
    }
    int len = s1.length();
    
    boolean[][][] f = new boolean[len][len][len + 1];
    for (int i = 0; i < len; ++i) {
      for (int j = 0; j < len; ++j) {
        f[i][j][1] = (s1.charAt(i) == s2.charAt(j));
      }
    }
    
    for (int l = 2; l <= len; ++l) {
      int sLen = len - l;
      for (int i = 0; i <= sLen; ++i) {
        for (int j = 0; j <= sLen; ++j) {
          for (int w = 1; w < l; ++w) {
            if (f[i][j][w] && f[i + w][j + w][l - w]) {
              f[i][j][l] = true;
              break;
            }
            if (f[i][j + l - w][w] && f[i + w][j][l - w]) {
              f[i][j][l] = true;
              break;
            }
          }
        }
      }
    }
    return f[0][0][len];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode087ScrambleString one = new LeetCode087ScrambleString();
    String s1 = "abc";
    String s2 = "cba";
    System.out.print(one.isScramble(s1, s2));
  }

}
