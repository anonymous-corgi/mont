package leetcode.p201to250;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class LeetCode205IsomorphicStrings {

    private interface Method {
        boolean isIsomorphic(String s, String t);
    }

    private static class PositionMap implements Method {

        @Override
        public boolean isIsomorphic(String s, String t) {
            int[] m = new int[256];
            for (int i = 0; i < s.length(); i++) {
                if (m[s.charAt(i)] != m[t.charAt(i) + 128]) {
                    return false;
                }
                m[s.charAt(i)] = m[t.charAt(i) + 128] = i + 1;
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

    @Test
    public void testcase1() {
        test("egg", "add", true);
    }

    @Test
    public void testcase2() {
        test("ab", "aa", false);
    }

    private void test(String s, String t, boolean expect) {
        Method method = getMethod();
        Assert.assertThat(method.isIsomorphic(s, t), is(expect));
    }
}
