package algorithm.leetcode.p001to050;

public class LeetCode003LongestSubstringWithoutRepeatingCharacters {
  
  public int lengthOfLongestSubstring(String s) {
    int max = 0;
    char[] chs = s.toCharArray();
    int[] lastIndex = new int[128];
    for(int start = 0, end = 0; end < s.length(); end++) {
      start = Math.max(lastIndex[chs[end]], start);
      lastIndex[chs[end]] = end + 1;
      max = Math.max(end - start + 1, max);
    }
    return max;
  }

  public static void main(String[] args) {
    LeetCode003LongestSubstringWithoutRepeatingCharacters one =
        new LeetCode003LongestSubstringWithoutRepeatingCharacters();
    String s = "abcabcbb";
    System.out.println(one.lengthOfLongestSubstring(s));
  }

}
