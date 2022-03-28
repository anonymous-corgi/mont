package algorithm.leetcode.p701to750;

public class LeetCode727MinimumWindowSubsequence {
  
  public static class Subarray_mothod {
    
    public String minWindow(String S, String T) {
      int index = 0;
      int minLeft = -1;
      int minRight = -1;
      int maxLen = Integer.MAX_VALUE;
      while (index <= S.length() - T.length()) {
        int tIndex = 0;
        int left = -1;
        int right = -1;
        for (int i = index; i < S.length(); i++) {
          if (S.charAt(i) == T.charAt(tIndex)) {
            tIndex++;
          }
          if (tIndex == 1 && left == -1) {
            left = i;
            index = left + 1;
          }
          if (tIndex == T.length()) {
            right = i + 1;
            break;
          }
        }
        if (left == -1 || right == -1) {
          break;
        }
        if (right - left < maxLen) {
          minLeft = left;
          minRight = right;
          maxLen = right - left;
        }
      }
      if (minLeft == -1 || minRight == -1) {
        return "";
      }
      return S.substring(minLeft, minRight);
    }
    
  }

  public static void main(String[] args) {

  }

}
