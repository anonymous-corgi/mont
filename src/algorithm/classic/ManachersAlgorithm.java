package algorithm.classic;

/**
 * @see leetcode.p001to050.LeetCode005LongestPalindromicSubstring
 */
public class ManachersAlgorithm {

    private static final char SEPARATOR = '*';

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        char[] chs = addBoundaries(s.toCharArray());
        int[] p = new int[chs.length];
        // c is the center of the current Palindrome.
        // m is the right margin of the current Palindrome.
        int c = 0, m = 0;
        // l: left, r: right. The two walking indexes to compare if the two elements are the same.
        int l = 0, r = 0;
        for (int i = 0; i < chs.length; i++) {
            // i >= m indicates that we can't use the p[im] to simplify calculation.
            if (i >= m) {
                r = i + 1;
                l = i - 1;
            } else {
                // im is the mirror index of i as to c.
                int im = c * 2 - i;
                if (i + p[im] >= m) {
                    p[i] = m - i;
                    r = m + 1;
                    l = i * 2 - r;
                } else {
                    p[i] = p[im];
                    l = -1; // This signal bypasses the while loop below.
                }
            }

            while (l >= 0 && r < chs.length && chs[l] == chs[r]) {
                p[i]++;
                r++;
                l--;
            }

            // Update m
            if ((i + p[i]) > m) {
                c = i;
                m = i + p[i];
            }
        }

        // Search the longest Palindrome.
        int maxC = 0, maxLen = 0;
        for (int i = 1; i < chs.length; i++) {
            if (p[i] > maxLen) {
                maxC = i;
                maxLen = p[i];
            }
        }

        return removeBoundaries(chs, maxC - maxLen, maxC + maxLen + 1);
    }

    private static char[] addBoundaries(char[] cs) {
        if (cs == null || cs.length == 0) {
            return new char[]{SEPARATOR};
        }

        char[] chs = new char[cs.length * 2 + 1];
        for (int i = 0; i < (chs.length - 1); i = i + 2) {
            chs[i] = SEPARATOR;
            chs[i + 1] = cs[i / 2];
        }
        chs[chs.length - 1] = SEPARATOR;
        return chs;
    }

    private static String removeBoundaries(char[] cs, int start, int end) {
        if (cs == null || cs.length < 3) {
            return "";
        }

        char[] res = new char[(end - start) / 2];
        for (int i = start + 1, j = 0; i < end; i += 2, j++) {
            res[j] = cs[i];
        }
        return String.valueOf(res);
    }
}
