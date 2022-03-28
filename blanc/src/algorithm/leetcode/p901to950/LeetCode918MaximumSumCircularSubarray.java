package algorithm.leetcode.p901to950;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode918MaximumSumCircularSubarray {
    private final int[] A;
    private final int expected;

    public LeetCode918MaximumSumCircularSubarray(int[] A, int expected) {
        this.A = A;
        this.expected = expected;
    }

    private interface Method {
        int maxSubarraySumCircular(int[] A);
    }

    public static final class Dequeue implements Method {

        public int maxSubarraySumCircular(int[] A) {
            int max = Integer.MIN_VALUE;
            int[] prefix = new int[A.length * 2 + 1];
            for (int i = 0, iLen = prefix.length - 1; i < iLen; i++) {
                prefix[i + 1] = prefix[i] + A[i % A.length];
            }
            Deque<Integer> minCandidates = new ArrayDeque<>(A.length + 1);
            minCandidates.offer(0);
            for (int i = 1; i < prefix.length; i++) {
                // If the minCandidate exceed one round.
                while (!minCandidates.isEmpty() && minCandidates.peekFirst() < i - A.length) {
                    minCandidates.pollFirst();
                }
                max = Math.max(max, prefix[i] - prefix[minCandidates.peekFirst()]);
                while (!minCandidates.isEmpty() && prefix[minCandidates.peekLast()] >= prefix[i]) {
                    minCandidates.pollLast();
                }
                minCandidates.offer(i);
            }
            return max;
        }
    }

    public static final class Discuss implements Method {

        public int maxSubarraySumCircular(int[] A) {
            int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
            for (int a : A) {
                curMax = Math.max(curMax + a, a);
                maxSum = Math.max(maxSum, curMax);
                curMin = Math.min(curMin + a, a);
                minSum = Math.min(minSum, curMin);
                total += a;
            }
            return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
        }
    }

    private Method getMethod() {
        return new Dequeue();
    }

    private void test(int[] A, int expected) {
        int actual = getMethod().maxSubarraySumCircular(A);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test(A, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[]{5, -3, 5}, 10},
                {new int[]{-2, -3, -1}, -1},
                {new int[]{3, -2, 2, -3}, 3},
                {new int[]{1, -2, 3, -2}, 3},
                {new int[]{3, -1, 2, -1}, 4},
                {new int[]{3, 1, 3, 2, 6}, 15},
        };
    }
}