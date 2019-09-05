package leetcode.p401to450;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * LeetCode 410. Split Array Largest Sum - Identical with: LintCode 437. Copy Book
 * Hard
 * <p>
 * Given an array which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 * <p>
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * Output:
 * 18
 * <p>
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
@SuppressWarnings("unused")
public class LeetCode410SplitArrayLargestSum {

    private interface Method {
        int splitArray(int[] nums, int m);
    }

    private static abstract class DP implements Method {

        @Override
        public int splitArray(int[] nums, int m) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            long[] dp = new long[nums.length + 1];
            long[] prefix = new long[nums.length + 1];
            for (int i = 1; i < dp.length; i++) {
                prefix[i] = prefix[i - 1] + nums[i - 1];
                dp[i] = prefix[i];
            }

            //  dp[i, s] means The optimal solution for the first i numbers that are divided into s groups.
            //  Updating dp[i, s] to dp[i, s + 1], we need to let the last group
            //  to include numbers from j + 1 to i,
            //  while the s groups divides the first j numbers (dp[j, s])
            //  And try to find a j making Max(dp[j, s], S[i] - S[j]) smallest
            //  => dp[i, s + 1] = Min( Max(dp[j, s], S[i] - S[j]) ),  s <= j < i
            for (int s = 2; s <= m; s++) {
                //  i indexes the length of subArray
                for (int pos = dp.length - 1; pos >= s; pos--) {
                    dp[pos] = findMin(prefix, dp, s, pos);
                }
            }
            return (int) dp[nums.length];
        }

        // Find a partition which makes the maximum of the two parts minimum.
        abstract int findMin(long[] prefix, long[] dp, int share, int total);
    }

    private static class DP_Binary extends DP {

        @Override
        int findMin(long[] prefix, long[] dp, int share, int total) {
            long min = Integer.MAX_VALUE;
            int start = share - 1;
            int end = total - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                long lastShare = prefix[total] - prefix[mid];
                long diff = dp[mid] - lastShare;
                if (diff < 0) {
                    start = mid + 1;
                } else if (diff > 0) {
                    end = mid - 1;
                } else {
                    return (int) lastShare;
                }
                min = Math.min(min, Math.max(dp[mid], lastShare));
            }
            return (int) Math.min(min, Math.max(dp[start], prefix[total] - prefix[start]));
        }
    }

    private static class DP_Pro extends DP {

        @Override
        int findMin(long[] prefix, long[] dp, int share, int total) {
            //  prefix[i] - prefix[j] means the sum for the s-th part.
            long min = Integer.MAX_VALUE;
            for (int j = total - 1; j >= share - 1; j--) {
                long max = Math.max(dp[j], prefix[total] - prefix[j]);
                if (max > min) {
                    break;
                }
                min = max;
            }
            return (int) min;
        }
    }

    private static class BinarySearch implements Method {

        @Override
        public int splitArray(int[] nums, int m) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            long start = 0;
            long end = 0;
            for (int num : nums) {
                start = Math.max(start, num);
                end += num;
            }
            while (start < end) {
                long mid = (end + start) / 2;
                if (canSplit(nums, mid, m)) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return (int) start;
        }

        private boolean canSplit(int[] nums, long maxSum, int maxGroups) {
            int count = 1;
            long sum = 0;
            for (int num : nums) {
                sum += num;
                if (sum > maxSum) {
                    count++;
                    sum = num;
                }
                if (count > maxGroups) {
                    return false;
                }
            }
            return true;
        }

    }

    private static Method getMethod() {
        return new DP_Binary();
    }

    @Test
    public void testcase1() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        test(nums, m, 18);
    }

    @Test
    public void testcase2() {
        int[] nums = {1, 2147483647};
        int m = 2;
        test(nums, m, 2147483647);
    }

    @Test
    public void testcase3() {
        int[] nums = {1, 4, 4};
        int m = 3;
        test(nums, m, 4);
    }

    private void test(int[] nums, int m, int expected) {
        Method method = getMethod();
        int actual = method.splitArray(nums, m);
        assertThat(actual, is(expected));
    }
}
