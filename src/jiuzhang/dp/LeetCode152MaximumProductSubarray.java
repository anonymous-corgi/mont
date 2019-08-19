package jiuzhang.dp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LeetCode152MaximumProductSubarray {

    private interface Method {
        int maxProduct(int[] nums);
    }

    private static class Normal implements Method {

        @Override
        public int maxProduct(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int max = nums[0];
            int min = nums[0];
            int result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < 0) {
                    int temp = max;
                    max = min;
                    min = temp;
                }
                max = Math.max(nums[i], nums[i] * max);
                min = Math.min(nums[i], nums[i] * min);
                result = Math.max(result, max);
            }
            return result;
        }
    }

    private static Method getMethod() {
        return new Normal();
    }

    @Test
    public void testcase1() {
        int[] nums = {2, 3, -2, 4};
        test(nums, 6);
    }

    @Test
    public void testcase2() {
        int[] nums = {-4};
        test(nums, -4);
    }

    private void test(int[] nums, int expect) {
        assertThat(getMethod().maxProduct(nums), is(expect));
    }
}
