package jiuzhang.dp.longestincreasingsubsequence;

import java.util.Arrays;

public class LeetCode300LongestIncreasingSubsequence {

  interface Method {
    int lengthOfLIS(int[] nums);
  }

  //DP_n2_method:
  public static class DP_n2_method implements Method {

    @Override
    public int lengthOfLIS(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int max = 1;
      int[] dp = new int[nums.length];
      for (int i = 0; i < nums.length; i++) {
        dp[i] = 1;
        for (int j = 0; j < i; j++) {
          if (nums[i] > nums[j]) {
            dp[i]= Math.max(dp[j] + 1, dp[i]);
          }
        }
        max = Math.max(max, dp[i]);
      }
      return max;
    }
    
  }
  
  //DP_nlogn_method:
  public static class DP_nlogn_method implements Method {

    @Override
    public int lengthOfLIS(int[] nums) {
      if(nums.length == 0){
        return 0;
      }
      
      int[] dp = new int [nums.length];
      int len = 0;
      for(int n : nums){
        int idx = Arrays.binarySearch(dp, 0, len, n);
        if(idx < 0) {
          idx = -(idx + 1);
        }
        dp[idx] = n;
        
        if(idx == len){
          len++;
        }
      }
      return len;
    }
    
  }

  private Method getMethod() {
    int type = 0;
    switch (type) {
      case 0: return new DP_n2_method();
      default: return new DP_nlogn_method();
    }
  }

  public int lengthOfLIS(int[] nums) {
    return getMethod().lengthOfLIS(nums);
  }


  public static void main(String[] args) {
    LeetCode300LongestIncreasingSubsequence one =
        new LeetCode300LongestIncreasingSubsequence();
//    int[] nums= {88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59};
    int[] nums = {10,9,2,5,3,7,101,18};
    System.out.println(one.lengthOfLIS(nums));
  }

}
