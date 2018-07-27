package jiuzhang.dp.longestincreasingsubsequence;

import java.util.Arrays;

public class LeetCode300LongestIncreasingSubsequence {
  //Method1:
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
  //Method2:
  public int lengthOfLIS2(int[] nums) {
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
  //Method3:
  public int longestIncreasingSubsequence(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int[] leastNum = new int[nums.length + 1];//The least number in a sequence of the corresponding number of item
    leastNum[0] = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length + 1; i++) {
      leastNum[i] = Integer.MAX_VALUE;
    }
    
    for (int i = 0; i < nums.length; i++) {
      int index = binarySearch(leastNum, nums[i]);
      leastNum[index] = nums[i];
    }
    
    for (int i = nums.length; i >= 0; i--) {
      if (leastNum[i] != Integer.MAX_VALUE) {
        return i;
      }
    }
    return 0;
  }
  
  private int binarySearch(int[] leastNum, int target) {
    int start = 0;
    int end = leastNum.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (leastNum[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }
    return end;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode300LongestIncreasingSubsequence one = 
        new LeetCode300LongestIncreasingSubsequence();
//    int[] nums= {88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59};
    int[] nums = {10,9,2,5,3,7,101,18};
    System.out.println(one.lengthOfLIS2(nums));
  }

}
