package jiuzhang.dp.twoarrays;

public class LeetCode97InterleavingString {
  
  public boolean isInterleave(String s1, String s2, String s3) {
    // write your code here
    if (s1 == null || s2 == null) {
      return false;
    }
    int sLen1 = s1.length();
    int sLen2 = s2.length();
    if (s3.length() != (sLen1 + sLen2)) {
      return false;
    }
    boolean[][] f = new boolean[sLen1 + 1][sLen2 + 1];
    f[0][0] = true;
    for (int i = 1; i <= sLen1; i++) {
      f[i][0] = (f[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
    }
    for (int j = 1; j <= sLen2; j++) {
      f[0][j] = (f[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1));
    }
    for (int i = 1; i <= sLen1; i++) {
      for (int j = 1; j <= sLen2; j++) {
        int index = i + j - 1;
        f[i][j] =((f[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(index))) 
            || (f[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(index))));
      }
    }
    return f[sLen1][sLen2];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode97InterleavingString one = new LeetCode97InterleavingString();
    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbcbcac";
    System.out.println(one.isInterleave(s1, s2, s3));
  }

}
