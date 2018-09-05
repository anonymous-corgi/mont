package leetcode.p001to050;

public class LeetCode005LongestPalindromicSubstring {
  
  public static class ManachersAlgorithm {
    
    private static final char SEPARATOR = '*';
    
    public String longestPalindrome(String s) {
      if (s == null || s.length() == 0) {
        return "";
      }
      
      char[] chs = addBoundaries(s.toCharArray());
      int[] p = new int[chs.length];
      // c represents the center of the current Palindrome.
      // r represents the right radius of the current Palindrome.
      int c = 0, r = 0;
      // f: front, b: back. The walking indexes to compare if two elements are the same.
      int f = 0, b = 0; 
      for (int i = 0; i < chs.length; i++) {
        if (i > r) {
          b = i + 1;
          f = i - 1;
        } else {
          // im the mirror index of i to c.
          int im = c * 2 - i;
          if (p[im] + i < r) {
            p[i] = p[im];
            f = -1; // This signals bypassing the while loop below. 
          } else {
            p[i] = r - i;
            b = r + 1;
            f = i * 2 - b;
          }
        }
        
        while (f >= 0 && b < chs.length && chs[f] == chs[b]) {
          p[i]++;
          f--;
          b++;
        }
        
        if ((i + p[i]) > r) {
          c = i;
          r = i + p[i];
        }
      }
      
      // Search the longest Palindrome.
      int maxC = 0, maxLen = 0;
      for (int i = 1; i < chs.length; i++) {
        if (p[i] > maxLen) {
          maxC = i;
          maxLen = p[i];
        }
      }
      
      return removeBoundaries(chs, maxC - maxLen, maxC + maxLen + 1);
    }
    
    private char[] addBoundaries(char[] cs) {
      if (cs == null || cs.length == 0) {
        return "|".toCharArray();         
      }
      
      char[] chs = new char[cs.length * 2 + 1];
      for (int i = 0; i < (chs.length - 1); i = i + 2) {
        chs[i] = SEPARATOR;
        chs[i + 1] = cs[i / 2];
      }
      chs[chs.length - 1] = SEPARATOR;
      return chs;
    }
    
    private String removeBoundaries(char[] cs, int start, int end) {
      if (cs == null || cs.length < 3) {
        return "";
      }
      
      char[] res = new char[(end - start) / 2];
      for (int i = start + 1, j = 0; i < end; i += 2, j++) {
        res[j] = cs[i];
      }
      return String.valueOf(res);
    }
    
  }

  public static void main(String[] args) {

  }

}
