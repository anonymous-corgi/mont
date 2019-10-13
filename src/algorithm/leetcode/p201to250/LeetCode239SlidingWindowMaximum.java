package algorithm.leetcode.p201to250;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Similar to {@link algorithm.leetcode.p901to950.LeetCode918MaximumSumCircularSubarray}
 */
public class LeetCode239SlidingWindowMaximum {

    private interface Method {
        int[] maxSlidingWindow(int[] nums, int k);
    }

    private static final class Dequeue implements Method {

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < k || nums.length == 0) {
                return new int[0];
            }
            int[] res = new int[nums.length - k + 1];
            Deque<Integer> maxCandidates = new ArrayDeque<>(k + 1);
            for (int i = 0; i < nums.length; i++) {
                while (!maxCandidates.isEmpty() && i - maxCandidates.peekFirst() >= k) {
                    maxCandidates.pollFirst();
                }
                while (!maxCandidates.isEmpty() && nums[maxCandidates.peekLast()] <= nums[i]) {
                    maxCandidates.pollLast();
                }
                maxCandidates.offer(i);
                if (i >= k - 1) {
                    res[i - k + 1] = nums[maxCandidates.peekFirst()];
                }
            }
            return res;
        }
    }
}