package algorithm.leetcode.p651to700;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 698. Partition to K Equal Sum Subsets
 * Medium
 * <p>
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * <p>
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class LeetCode698PartitionToKEqualSumSubsets {

    private interface Method {
        boolean canPartitionKSubsets(int[] nums, int k);
    }

    private static final class DFS implements Method {

        @Override
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            Arrays.sort(nums);
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) {
                return false;
            }
            int avg = sum / k;
            return dfs(nums, 0, new boolean[nums.length], avg, avg, k);
        }

        private boolean dfs(int[] nums, int start, boolean[] used, int remain, int target, int k) {
            if (k == 0) {
                return true;
            }
            // 'remain == target' means that we are looking the first number of a subset,
            // and we only pick the first unused number, so loop == 1.
            int loop = remain == target ? 1 : nums.length;
            boolean canPartition = false;
            for (int i = start; i < nums.length && loop > 0 && !canPartition; i++) {
                if (used[i] || (i != start && !used[i - 1] && nums[i - 1] == nums[i])) {
                    continue;
                }
                loop--;
                int newRemain = remain - nums[i];
                // nums is sorted in ascending.
                if (newRemain < 0) {
                    break;
                }
                used[i] = true;
                if (newRemain == 0) {
                    canPartition = dfs(nums, 0, used, target, target, k - 1);
                } else {
                    canPartition = dfs(nums, i + 1, used, newRemain, target, k);
                }
                used[i] = false;
            }
            return canPartition;
        }
    }

    private static Method getMethod() {
        return new DFS();
    }

    private void test(int[] nums, int k, boolean expected) {
        Method method = getMethod();
        boolean actual = method.canPartitionKSubsets(nums, k);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        boolean expected = true;
        test(nums, k, expected);
    }
}
