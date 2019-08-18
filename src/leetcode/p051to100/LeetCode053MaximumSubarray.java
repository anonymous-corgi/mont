package leetcode.p051to100;

public class LeetCode053MaximumSubarray {

    private interface Method {
        int maxSubArray(int[] nums);
    }

    private static class Greedy implements Method {

        @Override
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                sum += num;
                max = Math.max(max, sum);
                sum = Math.max(sum, 0);
            }
            return max;
        }
    }
}
