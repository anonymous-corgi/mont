package leetcode.p101to150;

import java.util.Arrays;

/**
 * LeetCode 132. Palindrome Partitioning II
 * Hard
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * Example:
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class LeetCode132PalindromePartitioningII {

    private interface Method {
        int minCut(String s);
    }

    private static class Manachers_method implements Method {

        @Override
        public int minCut(String s) {
            char[] chs = s.toCharArray();
            int[] p = getP(addBoundary(chs));

            int[] dp = new int[s.length() + 1];
            Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);
            for (int end = 1; end < dp.length; end++) {
                for (int start = end - 1; start >= 0; start--) {
                    if (p[start + end] >= end - start) {
                        dp[end] = Math.min(dp[end], dp[start] + 1);
                    }
                }
            }

            return dp[dp.length - 1] - 1;
        }

        private int[] getP(char[] chs) {
            int[] p = new int[chs.length];
            int c = 0, m = 0;
            int l = 0, r = 0;
            for (int i = 1; i < chs.length; i++) {
                if (i >= m) {
                    r = i + 1;
                    l = i - 1;
                } else {
                    int im = c * 2 - i;
                    if (i + p[im] >= m) {
                        p[i] = m - i;
                        r = m + 1;
                        l = i * 2 - r;
                    } else {
                        p[i] = p[im];
                        l = -1;
                    }
                }

                while (l >= 0 && r < chs.length && chs[l] == chs[r]) {
                    p[i]++;
                    l--;
                    r++;
                }

                if (i + p[i] > m) {
                    r = i;
                    m = i + p[i];
                }
            }
            return p;
        }

        private char[] addBoundary(char[] chs) {
            char[] res = new char[chs.length * 2 + 1];
            res[0] = '*';
            for (int i = 1; i < res.length; i += 2) {
                res[i] = chs[i / 2];
                res[i + 1] = '*';
            }
            return res;
        }
    }
}
