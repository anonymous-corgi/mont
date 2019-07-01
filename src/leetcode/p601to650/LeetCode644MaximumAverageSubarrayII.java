package leetcode.p601to650;

/**
 * 644. Maximum Average Subarray II
 * Hard
 * <p>
 * Description
 * Given an array consisting of n integers,
 * find the contiguous subarray whose length is greater than or equal to k that has the maximum average value.
 * And you need to output the maximum average value.
 * <p>
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation:
 * when length is 5, maximum average value is 10.8,
 * when length is 6, maximum average value is 9.16667.
 * Thus return 12.75.
 */
public class LeetCode644MaximumAverageSubarrayII {

    interface Method {
        double findMaxAverage(int[] nums, int k);
    }

    public static class Trial_Method implements Method {

        @Override
        public double findMaxAverage(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k > nums.length) {
                throw new IllegalArgumentException("Invalid input");
            }

            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            for (int num : nums) {
                max = Math.max(max, num);
                min = Math.min(min, num);
            }

            while (max - min > 1e-6d) {
                double guessAvg = (max + min) / 2.0d;
                if (evaluate(nums, k, guessAvg)) {
                    min = guessAvg;
                } else {
                    max = guessAvg;
                }
            }

            return max;
        }

        private boolean evaluate(int[] nums, int k, double guessAvg) {
            int len = nums.length;
            double min = 0;
            double[] sums = new double[len + 1];
            for (int i = 1; i <= len; i++) {
                sums[i] = sums[i - 1] + (nums[i - 1] - guessAvg);
                if (i >= k) {
                    min = Math.min(min, sums[i - k]);
                    if (sums[i] - min >= 0.0d) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
