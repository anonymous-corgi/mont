package leetcode.p451to500;

public class LeetCode486PredictTheWinner {
  
  public boolean PredictTheWinner(int[] nums) {
    int[][] dp = new int[nums.length][nums.length];
    for (int i = 0; i < nums.length; i++) {
      dp[i][i] = nums[i];
    }
    for (int g = 1; g < nums.length; g++) {
      for (int s = 0, sLen = nums.length - g; s < sLen; s++) {
        int e = s + g;
        dp[s][e] = Math.max(nums[s] - dp[s + 1][e], nums[e] - dp[s][e - 1]);
      }
    }
    return dp[0][nums.length - 1] >= 0;
  }
  
  public static void main(String[] args) {
    
  }

}
