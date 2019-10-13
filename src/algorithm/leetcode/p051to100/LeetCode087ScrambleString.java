package algorithm.leetcode.p051to100;

public class LeetCode087ScrambleString {

    private interface Method {
        boolean isScramble(String s1, String s2);
    }

    private static class DP implements Method {

        @Override
        public boolean isScramble(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() != s2.length()) {
                return false;
            }
            int sLen = s1.length();
            boolean[][][] dp = new boolean[sLen][sLen][sLen + 1];
            for (int i = 0; i < sLen; ++i) {
                for (int j = 0; j < sLen; ++j) {
                    dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
                }
            }

            for (int len = 2; len <= sLen; ++len) {
                int last = sLen - len;
                for (int start1 = 0; start1 <= last; start1++) {
                    for (int start2 = 0; start2 <= last; start2++) {
                        for (int w = 1; w < len; w++) {
                            dp[start1][start2][len] |= (dp[start1][start2][w] && dp[start1 + w][start2 + w][len - w])
                                    || (dp[start1][start2 + len - w][w] && dp[start1 + w][start2][len - w]);
                            if (dp[start1][start2][len]) {
                                break;
                            }
                        }
                    }
                }
            }
            return dp[0][0][sLen];
        }
    }

    private static Method getMethod() {
        return new DP();
    }
}
