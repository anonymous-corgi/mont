package leetcode.p051to100;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * LeetCode 097. Interleaving String
 * Hard
 * <p>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * <p>
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class LeetCode097InterleavingString {

    private interface Method {
        boolean isInterleave(String s1, String s2, String s3);
    }

    private static class DP implements Method {

        @Override
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            int sLen1 = s1.length();
            int sLen2 = s2.length();
            boolean[][] f = new boolean[sLen1 + 1][sLen2 + 1];
            f[0][0] = true;
            for (int i = 1; i <= sLen1 && f[i - 1][0]; i++) {
                f[i][0] = (f[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
            }
            for (int j = 1; j <= sLen2 && f[0][j - 1]; j++) {
                f[0][j] = (f[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1));
            }
            for (int i = 1; i <= sLen1; i++) {
                for (int j = 1; j <= sLen2; j++) {
                    int index = i + j - 1;
                    f[i][j] = (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(index))
                            || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(index));
                }
            }
            return f[sLen1][sLen2];
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    private boolean isInterleave(String s1, String s2, String s3) {
        return getMethod().isInterleave(s1, s2, s3);
    }

    @Test
    public void testIsInterleave1() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void testIsInterleave2() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        assertFalse(isInterleave(s1, s2, s3));
    }

    @Test
    public void testIsInterleave3() {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        assertTrue(isInterleave(s1, s2, s3));
    }
}
