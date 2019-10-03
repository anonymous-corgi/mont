package leetcode.p951to1000;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Similar to {@link leetcode.p551to600.LeetCode560SubarraySumEqualsK}
 */
public class LeetCode974SubarraySumsDivisibleByK {

    private interface Method {
        int subarraysDivByK(int[] A, int K);
    }

    private static final class Impl implements Method {

        public int subarraysDivByK(int[] A, int K) {
            if (A == null || A.length == 0) {
                return 0;
            }
            int prefix = 0;
            int res = 0;
            Map<Integer, Integer> sumsCount = new HashMap<>();
            sumsCount.put(0, 1);
            for (int a : A) {
                prefix = (prefix + a) % K;
                prefix = prefix < 0 ? prefix + K : prefix;
                res += sumsCount.getOrDefault(prefix, 0);
                sumsCount.compute(prefix, (k, v) -> v != null ? v + 1 : 1);
            }
            return res;
        }
    }

    private static Method getMethod() {
        return new Impl();
    }

    private void test(int[] A, int K, int expected) {
        int actual = getMethod().subarraysDivByK(A, K);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] A = new int[]{4, 5, 0, -2, -3, 1};
        int K = 5;
        test(A, K, 7);
    }
}
