package leetcode.p201to250;

public class LeetCode213HouseRobberII {
  
  public static class DP_method {
    
    public int rob(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      if (nums.length == 1) {
        return nums[0];
      }
      int[] zero = new int[nums.length + 1];
      int[] one = new int[nums.length + 1];
      zero[1] = nums[0];
      one[2] = nums[1];
      for (int i = 2; i < nums.length; i++) {
        zero[i] = Math.max(zero[i - 1], nums[i - 1] + zero[i - 2]);
      }
      for (int i = 3; i <= nums.length; i++) {
        one[i] = Math.max(one[i - 1], nums[i - 1] + one[i - 2]);
      }
      return Math.max(zero[nums.length - 1], one[nums.length]);
    }
    
  }
  
  
  public static class DP_Improved_method {
    
    public int rob(int[] nums) {
      if (nums.length == 1)
        return nums[0];
      return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }
    
    private int rob(int[] nums, int start, int end) {
      
      int minus1 = 0;
      int minus2 = 0;
      
      for (int i = start; i < end; i++) {
        int temp = minus1;
        minus1 = Math.max(minus1, nums[i] + minus2);
        minus2 = temp;
      }
      return minus1;        
    }
  
  }

  public static void main(String[] args) {

  }

}
