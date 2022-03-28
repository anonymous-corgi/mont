package algorithm.leetcode.p201to250;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class LeetCode201BitwiseAndOfNumbersRange {

    private interface Method {
        int rangeBitwiseAnd(int m, int n);
    }

    private static class Comparing implements Method {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            if (m == 0) {
                return 0;
            }
            int res = 0;
            int cursor = 1 << 30;
            while (cursor > n) {
                cursor >>= 1;
            }
            while ((cursor & m) == (cursor & n) && cursor > 0) {
                res += (cursor & m);
                cursor >>= 1;
            }
            return res;
        }
    }

    private static class Move implements Method {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            if (m == 0) {
                return 0;
            }
            int v1 = m;
            int v2 = n;
            int shift = 0;
            while (v1 != v2 && v1 != 0) {
                v1 >>= 1;
                v2 >>= 1;
                shift++;
            }
            return v1 << shift;
        }
    }

    private static Method getMethod() {
        return new Comparing();
    }

    @Test
    public void testcase1() {
        test(5, 7, 4);
    }

    private void test(int m, int n, int expect) {
        Assert.assertThat(getMethod().rangeBitwiseAnd(m, n), is(expect));
    }
}
