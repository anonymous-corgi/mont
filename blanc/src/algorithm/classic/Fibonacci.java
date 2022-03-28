package algorithm.classic;

import org.junit.Test;

import static algorithm.classic.Matrix.multiplyMatrix;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Fibonacci {

    //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233
    interface FibonacciNumber {
        long getFibonacciNumber(int n);
    }

    private static final class DP implements FibonacciNumber {

        @Override
        public long getFibonacciNumber(int n) {
            long[] dp = new long[]{0, 1, 1};

            if (n < 2) {
                return dp[n];
            }

            for (int i = 2; i < n; i++) {
                dp[0] = dp[1];
                dp[1] = dp[2];
                dp[2] = dp[0] + dp[1];
            }

            return dp[2];
        }
    }

    private static final class Matrix implements FibonacciNumber {

        //|f[n]   | = |1, 1| * |f[n - 1]|  => |1, 1|^(n - 1) * |f[1]|
        //|f[n -1]|   |1, 0|   |f[n - 2]|     |1, 0|           |f[0]|

        private final static long[][] BASE_MATRIX_Q = new long[][]{{1, 1}, {1, 0}};

        @Override
        public long getFibonacciNumber(int n) {
            if (n < 2) {
                return n;
            }
            long[][] matrixQ = getMatrixQ(n - 1, BASE_MATRIX_Q);

            return matrixQ[0][0];
        }

        private long[][] getMatrixQ(int n, long[][] matrix) {
            if (n == 1) {
                return matrix;
            }
            long[][] neoMatrixQ = getMatrixQ(n / 2, matrix);
            return n % 2 == 0
                    ? multiplyMatrix(neoMatrixQ, neoMatrixQ)
                    : multiplyMatrix(matrix, multiplyMatrix(neoMatrixQ, neoMatrixQ));
        }
    }

    private static FibonacciNumber getTestInstance() {
        return new Matrix();
    }

    private void test(int num, long expected) {
        assertThat(getTestInstance().getFibonacciNumber(num), equalTo(expected));
    }

    @Test
    public void testGetFibonacciNumber1() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        long[] expecteds = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233};
        for (int i = 0; i < expecteds.length; i++) {
            test(nums[i], expecteds[i]);
        }
    }
}
