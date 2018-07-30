package leetcode.p601to700;

public class LeetCode643MaximumAverageSubarray {
  
  public double findMaxAverage(int[] nums, int k) {
    // Write your code here
    if (nums == null || nums.length == 0) {
      return 0d;
    }
    int sum = 0;
    int index = 0;
    while (index < k) {
      sum += nums[index++];
    }
    int max = sum;
    while (index < nums.length) {
      sum += (nums[index] - nums[index++ - k]);
      max = Math.max(max, sum);
    }
    return (double) max / k;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
