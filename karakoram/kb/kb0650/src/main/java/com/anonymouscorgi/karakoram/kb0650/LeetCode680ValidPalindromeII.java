package com.anonymouscorgi.karakoram.kb0650;

/**
 * LeetCode 680. Valid Palindrome II
 * <p>
 * Given a string s, return true if the s can be palindrome after deleting at most one character
 * from it.
 * <p>
 * Example 1:
 * <li>Input: s = "aba"
 * <li>Output: true
 * <p>
 * Example 2:
 * <li>Input: s = "abca"
 * <li>Output: true
 * <li>Explanation: You could delete the character 'c'.
 * <p>
 * Example 3:
 * <li>Input: s = "abc" Output: false
 */
interface LeetCode680ValidPalindromeII {

  boolean validPalindrome(String s);

  LeetCode680ValidPalindromeII Method = new LeetCode680ValidPalindromeII() {

    @Override
    public boolean validPalindrome(String s) {
      return validPalindrome(s, 0, s.length() - 1, 1);
    }

    private static boolean validPalindrome(String s, int start, int end, int mismatchChance) {
      if (mismatchChance < 0) {
        return false;
      }
      int left = start;
      int right = end;
      while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
          return validPalindrome(s, left, right - 1, mismatchChance - 1)
              || validPalindrome(s, left + 1, right, mismatchChance - 1);
        }
        left++;
        right--;
      }
      return true;
    }
  };
}
