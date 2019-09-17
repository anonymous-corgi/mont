package leetcode.p201to250;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 205. Isomorphic Strings
 * Easy
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character
 * while preserving the order of characters. No two characters may map to the same character
 * but a character may map to itself.
 * <p>
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * <p>
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class LeetCode205IsomorphicStrings {

    private interface Method {
        boolean isIsomorphic(String s, String t);
    }

    private static class PositionMap implements Method {

        @Override
        public boolean isIsomorphic(String s, String t) {
            int[] m = new int[256];
            for (int i = 0; i < s.length(); i++) {
                int sp = s.charAt(i);
                int tp = t.charAt(i) + 128;
                if (m[sp] != m[tp]) {
                    return false;
                }
                m[sp] = m[tp] = i + 1;
            }
            return true;
        }
    }

    private static class TwoWayMap implements Method {

        @Override
        public boolean isIsomorphic(String s, String t) {
            char[] st = new char[128];
            char[] ts = new char[128];
            for (int i = 0, iLen = s.length(); i < iLen; i++) {
                char charS = s.charAt(i);
                char charT = t.charAt(i);
                if (st[charS] == 0 && ts[charT] == 0) {
                    st[charS] = charT;
                    ts[charT] = charS;
                } else if (st[charS] != charT || ts[charT] != charS) {
                    return false;
                }
            }
            return true;
        }
    }

    private static Method getMethod() {
        return new PositionMap();
    }

    private void test(String s, String t, boolean expect) {
        Method method = getMethod();
        assertThat(method.isIsomorphic(s, t), is(expect));
    }

    @Test
    public void testcase1() {
        test("egg", "add", true);
    }

    @Test
    public void testcase2() {
        test("ab", "aa", false);
    }

    @Test
    public void testcase3() {
        test("aa", "ab", false);
    }
}
