package leetcode.p1001to1050;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Similar to {@link leetcode.p401to450.LeetCode403FrogJump}
 */
@RunWith(Parameterized.class)
public class LeetCode1027LongestArithmeticSequence {
    private final int[] A;
    private final int expected;

    public LeetCode1027LongestArithmeticSequence(int[] A, int expected) {
        this.A = A;
        this.expected = expected;
    }

    private interface Method {
        int longestArithSeqLength(int[] A);
    }

    private static final class DP implements Method {

        public int longestArithSeqLength(int[] A) {
            int res = 0;
            Map<Integer, Integer>[] dp = new Map[A.length];
            for (int i = 0; i < A.length; i++) {
                dp[i] = new HashMap<>();
                for (int j = 0; j < i; j++) {
                    int diff = A[i] - A[j];
                    int num = dp[j].getOrDefault(diff, 1) + 1;
                    dp[i].put(diff, num);
                    res = Math.max(res, num);
                }
            }
            return res;
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    private void test(int[] A, int expected) {
        int actual = getMethod().longestArithSeqLength(A);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test(A, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[]{3, 6, 9, 12}, 4},
                {new int[]{9, 4, 7, 2, 10}, 3},
                {new int[]{20, 1, 15, 3, 10, 5, 8}, 4},
                {new int[]{3, 6, 2, 9, 5, 12, 8, 15}, 5},
        };
    }
}