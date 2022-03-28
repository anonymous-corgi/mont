package algorithm.leetcode.p001to050;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode050Pow {

    private interface Method {
        double myPow(double x, int n);
    }

    private static class Bit implements Method {

        @Override
        public double myPow(double x, int n) {
            long nLong = n;
            boolean isNegative = n < 0;
            nLong = isNegative ? -nLong : nLong;
            long bit = 1;
            double result = 1;
            while (nLong > 0) {
                if ((nLong & bit) != 0) {
                    result *= x;
                    nLong -= bit;
                }
                x *= x;
                bit <<= 1;
            }
            return isNegative ? 1 / result : result;
        }
    }

    private static Method getMethod() {
        return new Bit();
    }

    @Test
    public void testcase1() {
        test(2.00000, -2147483648, 0);
    }

    private void test(double x, int n, double expect) {
        assertThat(getMethod().myPow(x, n), is(expect));
    }
}
