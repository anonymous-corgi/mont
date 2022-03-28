package algorithm.leetcode.p151to200;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 161. One Edit Distance
 * Medium
 *
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * Note:
 *
 * There are 3 possiblities to satisify one edit distance apart:
 *
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * Example 3:
 *
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */
public class LeetCode161OneEditDistance {

    private interface Method {
        boolean isOneEditDistance(String s, String t);
    }

    private static final class Impl implements Method {

        public boolean isOneEditDistance(String s, String t) {
            if (Math.abs(s.length() - t.length()) > 1) {
                return false;
            }
            for (int i = 0, iLen = Math.min(s.length(), t.length()); i < iLen; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (s.length() < t.length()) {
                        return isEqual(s, i, t, i + 1);
                    } else if (s.length() > t.length()) {
                        return isEqual(s, i + 1, t, i);
                    } else {
                        return isEqual(s, i + 1, t, i + 1);
                    }
                }
            }
            return s.length() != t.length();
        }

        private boolean isEqual(String s, int sOffset, String t, int tOffset) {
            while (sOffset < s.length()) {
                if (s.charAt(sOffset++) != t.charAt(tOffset++)) {
                    return false;
                }
            }
            return true;
        }
    }
}