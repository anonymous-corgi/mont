package leetcode.p051to100;

import static org.junit.Assert.*;
import org.junit.Test;

public class LeetCode097InterleavingString {
  
  public boolean isInterleave(String s1, String s2, String s3) {
    int LEN1 = s1.length();
    int LEN2 = s2.length();
    if (LEN1 + LEN2 != s3.length()) { return false; }
    char[] chs1 = s1.toCharArray();
    char[] chs2 = s2.toCharArray();
    char[] chs3 = s3.toCharArray();
    boolean[][] dp = new boolean[LEN1 + 1][LEN2 + 1];
    dp[0][0] = true;
    for (int i = 1; i <= LEN1; i++) {
      if (!dp[i - 1][0]) { break; }
      dp[i][0] = chs1[i - 1] == chs3[i - 1];
    }
    for (int j = 1; j <= LEN2; j++) {
      if (!dp[0][j - 1]) { break; }
      dp[0][j] = chs2[j - 1] == chs3[j - 1];
    }
    for (int i = 1; i <= LEN1; i++) {
      for (int j = 1; j <= LEN2; j++) {
        dp[i][j] = (dp[i - 1][j] && chs1[i - 1] == chs3[i + j - 1]) 
                  || (dp[i][j - 1] && chs2[j - 1] == chs3[i + j - 1]);
      }
    }
    return dp[LEN1][LEN2];
  }
  
  
  @Test
  public void testIsInterleave1() {
    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbcbcac";
    assertTrue(isInterleave(s1, s2, s3));
  }
  
  @Test
  public void testIsInterleave2() {
    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbbaccc";
    assertFalse(isInterleave(s1, s2, s3));
  }
  
  @Test
  public void testIsInterleave3() {
    String s1 = "";
    String s2 = "";
    String s3 = "";
    assertTrue(isInterleave(s1, s2, s3));
  }
  
  

  public static void main(String[] args) {
    String s1 = "";
    String s2 = "";
    String s3 = "";
    
    LeetCode097InterleavingString one =
        new LeetCode097InterleavingString();
    
    System.out.println(one.isInterleave(s1, s2, s3));
  }

}
