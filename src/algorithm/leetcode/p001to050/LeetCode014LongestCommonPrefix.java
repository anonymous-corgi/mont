package algorithm.leetcode.p001to050;

import java.util.Arrays;

public class LeetCode014LongestCommonPrefix {
  
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    Arrays.sort(strs);
    char[] first = strs[0].toCharArray();
    char[] last = strs[strs.length - 1].toCharArray();
    int i = 0;
    int len = Math.min(first.length, last.length);
    for (; i < len; i++) {
      if (first[i] != last[i]) {
        break;
      }
    }
    return strs[0].substring(0, i);
  }

  public static void main(String[] args) {
    LeetCode014LongestCommonPrefix one =
        new LeetCode014LongestCommonPrefix();
    String[] strs = {"flower","flow","flight"};
    System.out.println(one.longestCommonPrefix(strs));
  }

}
