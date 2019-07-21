package jiuzhang.dp.coinschange;

/**
 * LeetCode 91. Decode Ways
 * Medium
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class LeetCode091DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            char pre = s.charAt(s.length() - i);
            char cur = s.charAt(s.length() - i - 1);
            if (cur == '0') {
                continue;
            }
            if (cur == '1' || (cur == '2' && pre < '7')) {
                dp[i] = dp[i - 2];
            }
            dp[i] += dp[i - 1];
        }
        return dp[s.length()];
    }
}
