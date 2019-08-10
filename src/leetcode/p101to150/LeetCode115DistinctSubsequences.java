package leetcode.p101to150;

public class LeetCode115DistinctSubsequences {

    private interface Method {
        int numDistinct(String s, String t);
    }

    private static class DP implements Method {

        @Override
        public int numDistinct(String s, String t) {
            if (s == null || t == null) {
                return 0;
            }
            // dp[sEnd][tEnd] means s.substring(0, sEnd) is able to create dp[sEnd][tEnd] distinct subsequences of t.substring(0, tEnd).
            int[][] dp = new int[s.length() + 1][t.length() + 1];
            for (int i = 0; i <= s.length(); i++) {
                dp[i][0] = 1;
            }
            for (int sEnd = 1; sEnd <= s.length(); sEnd++) {
                for (int tEnd = 1; tEnd <= t.length(); tEnd++) {
                    dp[sEnd][tEnd] = dp[sEnd - 1][tEnd];
                    if (s.charAt(sEnd - 1) == t.charAt(tEnd - 1)) {
                        dp[sEnd][tEnd] += dp[sEnd - 1][tEnd - 1];
                    }
                }
            }
            return dp[s.length()][t.length()];
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    /**
     * In point (4, 2)
     * d[3][2] means "abc" has d[3][2] "ab" not using the second "b"
     * +=d[2][1] means "ab" has d[2][1] "a", so we can used the second pair
     * of "b" to generate d[2][1] "ab"
     */
    public static void main(String[] args) {
        Method method = getMethod();
        String S = "abcb";
        String T = "ab";
        System.out.println(method.numDistinct(S, T));
    }
}
