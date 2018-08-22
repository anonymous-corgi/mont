package jiuzhang.dp.rangeproperties;

public class LeetCode312BurstBalloons {
  
  public static class  DP_method {
    
    public int maxCoins(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int len = nums.length;
      if (len == 1) {
        return nums[0];
      }
      int[][] dp = new int[len][len];
      for (int gap = 0, gLen = len - 2; gap < gLen; gap++) {
        for (int start = 1, sLen = len - 1 - gap; start < sLen; start++) {
          int end = start + gap;
          for (int pick = start; pick <= end; pick++) {
            int sum = nums[start - 1] * nums[pick] * nums[end + 1] 
                + dp[start][pick - 1] + dp[pick + 1][end];
            dp[start][end] = Math.max(dp[start][end] , sum); 
          }
        }
      }
      
      return dp[1][len - 2] + (nums[0] * nums[len - 1]) + Math.max(nums[0], nums[len - 1]);
    }
    
  }
  
  public static class  DFS_Memerization_method {
    
    public int maxCoins(int[] nums) {
      int n = nums.length;
      int[][] dp = new int [n+2][n+2];
      int[][] visit = new int[n+2][n+2]; 
      int [] arr = new int [n+2];
      for (int i = 1; i <= n; i++){
        arr[i] = nums[i-1];
      }
      arr[0] = 1;
      arr[n+1] = 1;
      int res = search(arr, dp, visit, 1 , n);
      return res;
    }
    
    public int search(int []arr, int [][]dp, int [][]visit, int left, int right) {
      if(visit[left][right] == 1) {
        return dp[left][right];
      }
      
      int res = 0;
      for (int k = left; k <= right; ++k) {
        int midValue =  arr[left - 1] * arr[k] * arr[right + 1];
        int leftValue = search(arr, dp, visit, left, k - 1);
        int rightValue = search(arr, dp, visit, k + 1, right);
        res = Math.max(res, leftValue + midValue + rightValue);
      }
      visit[left][right] = 1;
      dp[left][right] = res;
      return res;
    }
    
  }
  
  public static void main(String[] args) {
    LeetCode312BurstBalloons.DP_method one = 
        new LeetCode312BurstBalloons.DP_method();
    int[] nums = {4, 1, 5, 10};
    System.out.println(one.maxCoins(nums));
  }
  
}