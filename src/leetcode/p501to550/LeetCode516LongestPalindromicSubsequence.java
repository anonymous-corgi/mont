package leetcode.p501to550;

/**
 * LeetCode 516. Longest Palindromic Subsequence
 * Medium
 * <p>
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * <p>
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LeetCode516LongestPalindromicSubsequence {

    private interface Method {
        int longestPalindromeSubseq(String s);
    }

    private static class DP_Improved_method implements Method {

        @Override
        public int longestPalindromeSubseq(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            int sLen = s.length();
            int[][] dp = new int[sLen][sLen + 1];

            for (int start = sLen - 1; start >= 0; start--) {
                dp[start][start + 1] = 1;
                for (int end = start + 2; end <= sLen; end++) {
                    dp[start][end] = s.charAt(start) == s.charAt(end - 1)
                            ? dp[start + 1][end - 1] + 2
                            : Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
            return dp[0][sLen];
        }
    }

    private static class DP_method implements Method {

        @Override
        public int longestPalindromeSubseq(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            int sLen = s.length();
            int[][] dp = new int[sLen][sLen + 1];
            for (int i = 0; i < sLen; i++) {
                dp[i][i + 1] = 1;
            }
            for (int g = 2; g <= sLen; g++) {
                for (int i = 0, end = sLen - g; i <= end; i++) {
                    int e = i + g;
                    dp[i][e] = s.charAt(i) == s.charAt(e - 1)
                            ? dp[i + 1][e - 1] + 2
                            : Math.max(dp[i + 1][e], dp[i][e - 1]);
                }
            }
            return dp[0][sLen];
        }
    }
}
