package leetcode.p401to450;

public class LeetCode409LongestPalindrome {
  
  public int longestPalindrome(String s) {
    char[] chs = s.toCharArray();
    int[] count = new int[128];
    int res = 0;
    for (char c : chs) {
      if (count[c]++ % 2 == 1) {
        res++;
      }
    }
    return Math.min(res * 2 + 1, chs.length);
  }
  
  public static void main(String[] args) {

  }

}
