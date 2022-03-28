package algorithm.leetcode.p1101to1150;

/**
 * The same as {@link algorithm.lintcode.p051to100.LintCode077LongestCommonSubsequence}
 *
 * LeetCode 1143. Longest Common Subsequence
 * Medium
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some
 * characters(can be none) deleted without changing the relative order of the remaining characters.
 * (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * If there is no common subsequence, return 0.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 */
public class LeetCode1143LongestCommonSubsequence {

    private interface Method {
        int longestCommonSubsequence(String text1, String text2);
    }

    private static class DP implements Method {

        @Override
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
                return 0;
            }
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
            for (int i = 1; i <= text1.length(); i++) {
                for (int j = 1; j <= text2.length(); j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            return dp[text1.length()][text2.length()];
        }
    }
}
