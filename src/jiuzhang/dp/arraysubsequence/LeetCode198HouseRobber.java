package jiuzhang.dp.arraysubsequence;

public class LeetCode198HouseRobber {
  
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    int[] dp = new int[len + 1];
    dp[1] = nums[0];
    for (int i = 2; i <= len; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
    }
    return dp[len];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
