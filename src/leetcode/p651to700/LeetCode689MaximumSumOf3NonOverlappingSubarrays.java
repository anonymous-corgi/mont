package leetcode.p651to700;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class LeetCode689MaximumSumOf3NonOverlappingSubarrays {

    private interface Method {
        int[] maxSumOfThreeSubarrays(int[] nums, int k);
    }

    private static final class DP implements Method {

        @Override
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[][] dp = new int[3][nums.length - k + 2];
            long[] prefix = new long[nums.length - k + 2];
            for (int i = 0; i < k; i++) {
                prefix[1] += nums[i];
            }
            for (int i = k; i < nums.length; i++) {
                prefix[i - k + 2] += (prefix[i - k + 1] + nums[i] - nums[i - k]);
            }
            for (int i = 1; i < prefix.length; i++) {
                dp[0][i] = prefix[dp[0][i - 1]] >= prefix[i] ? dp[0][i - 1] : i;
            }
            for (int n = 1; n < 3; n++) {
                for (int total = prefix.length - 1, first = n * k; total >= first; total--) {
                    dp[n][total] = total;
                    for (int p = total; p >= first; p--) {
                        int sum = 0;
                        for (int c = n; c >= 0; c--) {
//                            sum += prefix[]
                        }
                        dp[n][total] = prefix[dp[n][total]] > prefix[p] ? dp[n][total] : p;
                    }
                }
            }
            int last = dp.length - 1;
            return new int[]{dp[0][last], dp[1][last], dp[2][last]};
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    @Test
    public void testcase1() {
        int[] nums = new int[]{1,2,1,2,6,7,5,1};
        int k = 2;
        int[] expected = new int[]{0, 3, 5};
        test(nums, k, expected);
    }

    private void test(int[] nums, int k, int[] expected) {
        Method method = getMethod();
        int[] actual = method.maxSumOfThreeSubarrays(nums, k);
        assertThat(actual, equalTo(expected));
    }
}
