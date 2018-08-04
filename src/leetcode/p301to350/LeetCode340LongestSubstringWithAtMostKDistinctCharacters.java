package leetcode.p301to350;

public class LeetCode340LongestSubstringWithAtMostKDistinctCharacters {
  
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    // write your code here
    if (s == null || s.length() == 0) {
      return 0;
    }
    int[] count = new int[128];
    char[] cs = s.toCharArray();
    int head = 0;
    int diffChar = 0;
    int maxLen = 0;
    for (int i = 0, iLen = s.length(); i < iLen; i++) {
      if (count[cs[i]]++ == 0) {
        diffChar++;
      }
      
      while (diffChar > k) {
        if (count[cs[head++]]-- == 1) {
          diffChar--;
        }
      }
      maxLen = Math.max(maxLen, i - head + 1);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
