package com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange;

/**
 * LeetCode 639. Decode Ways II
 * Hard
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 * <p>
 * Given the encoded message containing digits and the character '*',
 * return the total number of ways to decode it.
 * <p>
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 * <p>
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * <p>
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 * <p>
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */
final class LeetCode639DecodeWaysII {

    private static final long MOD = (long) (1E9 + 7);

    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(s.length() - 1) == '0' ? 0 : (s.charAt(s.length() - 1) == '*' ? 9 : 1);
        for (int i = 2; i <= s.length(); i++) {
            char cur = s.charAt(s.length() - i);
            char pre = s.charAt(s.length() - i + 1);
            if (cur == '0') {
                continue;
            }
            if (cur == '*') {
                if (pre == '*') {
                    dp[i] += dp[i - 2] * 15;
                } else if (pre < '7') {
                    dp[i] += dp[i - 2] * 2;
                } else {
                    dp[i] += dp[i - 2];
                }
                dp[i] += dp[i - 1] * 9;
            } else if (cur == '1') {
                if (pre == '*') {
                    dp[i] += dp[i - 2] * 9;
                } else {
                    dp[i] += dp[i - 2];
                }
                dp[i] += dp[i - 1];
            } else if (cur == '2') {
                if (pre == '*') {
                    dp[i] += dp[i - 2] * 6L;
                } else if (pre < '7') {
                    dp[i] += dp[i - 2];
                }
                dp[i] += dp[i - 1];
            } else {
                dp[i] += dp[i - 1];
            }

            if (dp[i] > MOD) {
                dp[i] %= MOD;
            }
        }
        return (int) dp[s.length()];
    }
}
