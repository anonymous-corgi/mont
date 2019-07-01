package jiuzhang.c6.arraylist;

public class M617MaximumAverageSubarray {

    public double maxAverage(int[] nums, int k) {
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

    public static void main(String[] args) {
        M617MaximumAverageSubarray one = new M617MaximumAverageSubarray();
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 3;
        one.maxAverage(nums, k);
    }
}
