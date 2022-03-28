package algorithm.leetcode.p301to350;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * Hard
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LeetCode340LongestSubstringWithAtMostKDistinctCharacters {

    private interface Method {
        int lengthOfLongestSubstringKDistinct(String s, int k);
    }

    private static final class Greedy implements Method {

        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int[] count = new int[128];
            int head = 0;
            int diffChar = 0;
            int maxLen = 0;
            for (int i = 0, iLen = s.length(); i < iLen; i++) {
                if (count[s.charAt(i)]++ == 0) {
                    diffChar++;
                }

                while (diffChar > k) {
                    if (count[s.charAt(head++)]-- == 1) {
                        diffChar--;
                    }
                }
                maxLen = Math.max(maxLen, i - head + 1);
            }
            return maxLen;
        }
    }
}
