package jiuzhang.dp.rangeproperties;

public class LeetCode312BurstBalloons {
  
  public int maxCoins(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    if (len == 1) {
      return nums[0];
    }
    int[][] f = new int[len][len];
    for (int l = 1, lLen = len - 1 ; l < lLen; l++) {
      for (int s = 1, sLen = len - l; s < sLen; s++) {
        int e = s + l - 1;
        for (int c = 0; c < l; c++) {
          int sum = nums[s - 1] * nums[s + c] * nums[e + 1] 
              + f[s][s + c - 1] + f[s + c + 1][e];
          f[s][e] = Math.max(f[s][e] , sum); 
        }
      }
    }
    
    return f[1][len - 2] + (nums[0] * nums[len - 1]) + Math.max(nums[0], nums[len - 1]);
  }
  
  public int maxCoins2(int[] nums) {
    int n = nums.length;
    int [][]dp = new int [n+2][n+2];
    int [][]visit = new int[n+2][n+2]; 
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
    if(visit[left][right] == 1)
      return dp[left][right];
    
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
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode312BurstBalloons one = new LeetCode312BurstBalloons();
    int[] nums = {4, 1, 5, 10};
    System.out.println(one.maxCoins(nums));
    System.out.println(one.maxCoins2(nums));
  }
  
}