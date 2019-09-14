package leetcode.p201to250;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 248. Strobogrammatic Number III
 * Hard
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * Example:
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 */
public class LeetCode248StrobogrammaticNumberIII {

    private interface Method {
        int strobogrammaticInRange(String low, String high);
    }

    private static final class DFS implements Method {

        @Override
        public int strobogrammaticInRange(String low, String high) {
            int[] res = new int[1];
            int[] lenLimit = new int[]{low.length(), high.length()};
            long[] valueLimit = new long[]{Long.parseLong(low), Long.parseLong(high)};
            dfs("", lenLimit, valueLimit, res);
            dfs("0", lenLimit, valueLimit, res);
            dfs("1", lenLimit, valueLimit, res);
            dfs("8", lenLimit, valueLimit, res);
            return res[0];
        }

        private void dfs(String str, int[] lenLimit, long[] valueLimit, int[] res) {
            if (str.length() > lenLimit[1]) {
                return;
            }
            if (str.length() >= lenLimit[0] && (str.length() == 1 || str.charAt(0) != '0')) {
                long value = Long.parseLong(str);
                if (value >= valueLimit[0] && value <= valueLimit[1]) {
                    res[0]++;
                }
            }

            dfs("0" + str + "0", lenLimit, valueLimit, res);
            dfs("1" + str + "1", lenLimit, valueLimit, res);
            dfs("6" + str + "9", lenLimit, valueLimit, res);
            dfs("8" + str + "8", lenLimit, valueLimit, res);
            dfs("9" + str + "6", lenLimit, valueLimit, res);
        }
    }

    private static final class DFS2 implements Method {

        @Override
        public int strobogrammaticInRange(String low, String high) {
            int[] res = new int[1];
            int[] startDigits = new int[]{0, 1, 8};
            long[] valueLimit = new long[]{Long.parseLong(low), Long.parseLong(high)};

            // For creating even digits number.
            dfs(0, 10, valueLimit, res);

            // For creating odd digits number.
            for (int i = 0; i < startDigits.length && startDigits[i] <= valueLimit[1]; i++) {
                if (valueLimit[0] <= startDigits[i]) {
                    res[0]++;
                }
                dfs(startDigits[i], 100, valueLimit, res);
            }
            return res[0];
        }

        private void dfs(long num, long factor, long[] valueLimit, int[] res) {
            num *= 10;
            long nextFactor = factor * 100;
            int[][] digits = new int[][]{{1, 1}, {6, 9}, {8, 8}, {9, 6}};
            // IMPORTANT: Avoid Infinite Loop: cause by num == 0.
            if (valueLimit[1] < num || valueLimit[1] < factor) {
                return;
            }
            dfs(num, nextFactor, valueLimit, res);
            for (int[] digit : digits) {
                long nextNum = factor * digit[0] + num + digit[1];
                if (valueLimit[1] < nextNum) {
                    break;
                }
                if (valueLimit[0] <= nextNum) {
                    res[0]++;
                }
                dfs(nextNum, nextFactor, valueLimit, res);
            }
        }
    }

    private static Method getMethod() {
        return new DFS2();
    }

    private void test(String low, String high, int expected) {
        Method method = getMethod();
        int actual = method.strobogrammaticInRange(low, high);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        test("0", "100", 7);
    }

    @Test
    public void testcase2() {
        test("50", "100", 3);
    }
}
