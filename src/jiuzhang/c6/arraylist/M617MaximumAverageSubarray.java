package jiuzhang.c6.arraylist;

public class M617MaximumAverageSubarray {
	
    public double maxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        double max = (double) Integer.MIN_VALUE;
        double min = (double) Integer.MAX_VALUE;
        
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            max = Math.max(max, (double) nums[i]);
            min = Math.min(min, (double) nums[i]);
        }
        
        while (max - min > 1e-6d) {
            // set the bar
            double bar = (max + min) / 2.0d;
            if (evaluate(nums, k, bar)) {
                // raise the bar
                min = bar;
            } else {
                // lower the bar
                max = bar;
            }
        }
        
        return max;
    }
    
    public int counter = 0;
    
    private boolean evaluate(int[] nums, int k, double bar) {
    	counter++;
        int n = nums.length;
        double[] sums = new double[n + 1];
        sums[0] = 0.0d;
        for (int i = 1; i <= n; i++) {
            // aggregate the differences between bar and element
            sums[i] = sums[i - 1] + (nums[i - 1] - bar);
            
            if (i >= k && sums[i] >= 0.0d) {
                // first ith elements average is above the bar
                return true;
            }
            
            if (i >= k) {
                // use sums[0] to track the min value
                sums[0] = Math.min(sums[0], sums[i - k]);
                if (sums[i] - sums[0] >= 0.0d) {
                    // elements could close the largest gap
                    return true;
                }
            }
        }
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M617MaximumAverageSubarray one = new M617MaximumAverageSubarray();
		int[] nums = {1, 12, -5, -6, 50, 3};
		int k = 3;
		one.maxAverage(nums, k);
		System.out.println(one.counter);
	}

}
