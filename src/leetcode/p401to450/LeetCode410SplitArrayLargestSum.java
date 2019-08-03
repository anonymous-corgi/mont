package leetcode.p401to450;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * LeetCode 410. Split Array Largest Sum
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
public class LeetCode410SplitArrayLargestSum {

    private interface Method {
        int splitArray(int[] nums, int m);
    }

    private static class BinarySearch_Method implements Method {

        @Override
        public int splitArray(int[] nums, int m) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            long left = 0;
            long right = 0;
            for (int num : nums) {
                left = Math.max(left, num);
                right += num;
            }
            while (left < right) {
                long mid = (right + left) / 2;
                if (canSplit(nums, mid, m)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return (int) right;
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

    private static class DP_Method implements Method {
        //  This method reverse the update way as to the previous one,
        //  making it much more similar to the DP solution given by Jiuzhang
        @Override
        public int splitArray(int[] nums, int m) {
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
            for (int s = 1; s < m; s++) {
                //  i indexes the length of subArray
                for (int i = dp.length - 1; i > s; i--) {
                    //  prefix[i] - prefix[j] means the sum for the s-th part.
                    for (int j = i - 1; j >= s; j--) {
                        long max = Math.max(dp[j], prefix[i] - prefix[j]);
                        if (max <= dp[i]) {
                            dp[i] = max;
                        } else {
                            break;
                        }
                    }
                }
            }
            return (int) dp[dp.length - 1];
        }
    }

    private static Method getMethod() {
        int num = 1;
        switch (num) {
            case 0:
                return new BinarySearch_Method();
            default:
                return new DP_Method();
        }
    }

    private int splitArray(int[] nums, int m) {
        return getMethod().splitArray(nums, m);
    }

    @Test
    public void testSplitArray() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        int res = 18;
        assertEquals(res, splitArray(nums, m));
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        Method method = getMethod();
        System.out.println(method.splitArray(nums, m));
    }
}
