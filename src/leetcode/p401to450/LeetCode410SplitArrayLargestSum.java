package leetcode.p401to450;

public class LeetCode410SplitArrayLargestSum {
  
  public static class BinarySearch_method {
    
    public int splitArray(int[] nums, int m) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      long left = nums[0];
      long right = nums[0];
      for (int i = 1; i < nums.length; i++) {
        left = Math.max(left, nums[i]);
        right += nums[i];
      }
      while (left < right) {
        long mid = (right + left) / 2;
        if (canSplit(nums, mid, m)) {
          right = mid;
        } else {
          left = mid + 1;
        }
      }
      return (int) right;
    }
    
    private boolean canSplit(int[] nums, long maxSum, int maxGroups) {
      int count = 1;
      long sum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (sum > maxSum) {
          count++;
          sum = nums[i];
        }
        if (count > maxGroups) {
          return false;
        }
      }
      return true;
    }
    
  }
  
  public static class DP_method {
//  This method reverse the update way as to the previous one,
//  making it much more similar to the DP solution given by Jiuzhang
    public int splitArray(int[] nums, int m) {
      int LEN = nums.length;
      int[] dp = new int[LEN + 1];
      int[] prefix = new int[LEN + 1];
      for(int i = 1; i <= LEN; i++) {
        prefix[i] = prefix[i - 1] + nums[i - 1];
        dp[i] = prefix[i];
      }
//     dp[i, s] means The optimal solution for the first i numbers that are divided into s groups.
//    Updating dp[i, s] to dp[i, s + 1], we need to let the last group 
//    to include numbers from j + 1 to i, 
//    while the s groups divides the first j numbers (dp[j, s])
//    And try to find a j making Max(dp[j, s], S[i] - S[j]) smallest
//    => dp[i, s + 1] = Min( Max(dp[j, s], S[i] - S[j]) ),  s <= j < i
      for(int s = 1; s < m; s++) {
//        i indexes the length of subArray
        for(int i = LEN; i > s; i--) {
          dp[i] = Integer.MAX_VALUE;
//        prefix[i] - prefix[j] means the sum for the s-th part.
          for(int j = i - 1; j >= s; j--) {
            int t = Math.max(dp[j], prefix[i] - prefix[j]);
            if(t <= dp[i]) {
              dp[i] = t;
            } else {
              break;
            }
          }
        }
      }
      return dp[LEN];
    }
    
  }

  public static void main(String[] args) {
    int[] nums = {7,2,5,10,8};
    int m = 3;
    LeetCode410SplitArrayLargestSum.BinarySearch_method one = 
        new LeetCode410SplitArrayLargestSum.BinarySearch_method();
    System.out.println(one.splitArray(nums, m));
  }

}
