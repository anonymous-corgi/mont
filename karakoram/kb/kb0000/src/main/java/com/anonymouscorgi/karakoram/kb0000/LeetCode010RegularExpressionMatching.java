package com.anonymouscorgi.karakoram.kb0000;

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for
 * '.' and '*' where:
 *
 * '.' Matches any single character.​​​​ '*' Matches zero or more of the preceding element. The
 * matching should cover the entire input string (not partial).
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "a*" Output: true Explanation: '*' means zero or more of the preceding
 * element, 'a'. Therefore, by repeating 'a' once, it becomes "aa". Example 3:
 *
 * Input: s = "ab", p = ".*" Output: true Explanation: ".*" means "zero or more (*) of any character
 * (.)". Constraints:
 *
 * 1 <= s.length <= 20 1 <= p.length <= 20 s contains only lowercase English letters. p contains
 * only lowercase English letters, '.', and '*'. It is guaranteed for each appearance of the
 * character '*', there will be a previous valid character to match. Related Topics String Dynamic
 * Programming Recursion
 */
interface LeetCode010RegularExpressionMatching {

  boolean isMatch(String s, String p);

  LeetCode010RegularExpressionMatching Method = new LeetCode010RegularExpressionMatching() {

    @Override
    public boolean isMatch(String s, String p) {
      if (s == null || p == null) {
        return false;
      }
      char[] ss = s.toCharArray();
      char[] ps = p.toCharArray();
      boolean[][] isMatch = new boolean[s.length() + 1][p.length() + 1];
      isMatch[0][0] = true;

      for (int i = 0; i < isMatch.length; i++) {
        for (int j = 1; j < isMatch[0].length; j++) {
          if (j > 1 && ps[j - 1] == '*') {
            //This is the situation neglecting the last two chars of p;
            isMatch[i][j] |= isMatch[i][j - 2];

            //This is the situation where the last char of s
            //and the last second char of p match.
            if (i > 0 && (ss[i - 1] == ps[j - 2]
                || ps[j - 2] == '.')) {
              isMatch[i][j] |= isMatch[i - 1][j];
            }
          }
          //This is the situation where last chars of both s and p match.
          else if (i > 0 && (ss[i - 1] == ps[j - 1]
              || ps[j - 1] == '.')) {
            isMatch[i][j] |= isMatch[i - 1][j - 1];
          }
        }
      }
      return isMatch[s.length()][p.length()];
    }
  };
}
