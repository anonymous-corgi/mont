package leetcode.p851to900;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Similar to {@link leetcode.p051to100.LeetCode084LargestRectangleInHistogram}
 */
@RunWith(Parameterized.class)
public class LeetCode862ShortestSubarrayWithSumAtLeastK {

    private final int[] nums;
    private final int k;
    private final int expected;

    public LeetCode862ShortestSubarrayWithSumAtLeastK(int[] nums, int k, int expected) {
        this.nums = nums;
        this.k = k;
        this.expected = expected;
    }

    private interface Method {
        int shortestSubarray(int[] A, int K);
    }

    // Two pointers doesn't work, because there might be negative number.
    // Under this situation, moving right pointer forward might not increase the subarray sum.
    private static final class Dequeue implements Method {

        public int shortestSubarray(int[] A, int K) {
            // Must initiate as Integer.MAX_VALUE, otherwise error while length == 1.
            int res = Integer.MAX_VALUE;
            Deque<Integer> deque = new ArrayDeque<>();
            int[] prefix = new int[A.length + 1];
            for (int i = 0; i < A.length; i++) {
                prefix[i + 1] += prefix[i] + A[i];
            }
            for (int cursor = 0; cursor < prefix.length; cursor++) {
                while (!deque.isEmpty() && prefix[cursor] - prefix[deque.peekFirst()] >= K) {
                    res = Math.min(res, cursor - deque.pollFirst());
                }

                // prefix[cursor] <= prefix[deque.peekLast()]  MEANS: prefix[x] - prefix[cursor] >= prefix[x] - prefix[deque.peekLast()]
                // also, cursor > deque.peekLast().
                // So, 1. If prefix[x] - prefix[deque.peekFirst()] >= k,
                //        must have prefix[x] - prefix[cursor] >= k,
                //        and x - cursor < x - deque.peekFirst()
                //     2. If prefix[x] - prefix[cursor] < k,
                //        must have prefix[x] - prefix[deque.peekFirst()] < k
                while (!deque.isEmpty() && prefix[cursor] <= prefix[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(cursor);
            }
            return res < Integer.MAX_VALUE ? res : -1;
        }
    }

    private Method getMethod() {
        return new Dequeue();
    }

    @Test
    public void test() {
        int actual = getMethod().shortestSubarray(nums, k);
        assertThat(actual, is(expected));
    }

    @Parameterized.Parameters
    public static Object[][] primeNumbers() {
        return new Object[][]{{new int[]{84, -37, 32, 40, 95}, 167, 3}, {new int[]{2, -1, 2}, 3, 3}};
    }
}
