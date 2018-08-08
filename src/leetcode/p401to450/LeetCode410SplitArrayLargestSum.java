package leetcode.p401to450;

public class LeetCode410SplitArrayLargestSum {
  
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
  
//  DP Method: (Is the same with LintCode437CopyBook)
  public int splitArrayDP(int[] nums, int m) {
    int L = nums.length;
    int[] S = new int[L + 1];
    S[0] = 0;
    for(int i = 0; i < L; i++) {
      S[i + 1] = S[i] + nums[i];
    }
    
    int[] dp = new int[L];
    for(int i = 0; i < L; i++) {
      dp[i] = S[L] - S[i];
    }
    
    for(int s = 1; s < m; s++) {
//      We only consider the number from i to L - S
      for(int i = 0; i < L - s; i++) {
        dp[i]=Integer.MAX_VALUE;
//        What if one part is number from i to j - 1 
//        and the part j to L is separated into s groups. 
        // The max sum of these s groups is dp[j]
        for(int j = i + 1; j <= L - s; j++) {
          int t = Math.max(dp[j], S[j] - S[i]);
          if(t <= dp[i])
            dp[i] = t;
          else
            break;
        }
      }
    }
    return dp[0];
  }
  
//  This method reverse the update way as to the previous one,
//  making it much more similar to the DP solution given by Jiuzhang
  public int splitArrayDP2(int[] nums, int m) {
    int L = nums.length;
    int[] S = new int[L + 1];
    int[] dp = new int[L + 1];
    S[0] = 0;
    for(int i = 0; i < L; i++) {
      S[i + 1] = S[i] + nums[i];
      dp[i + 1] = S[i + 1];
    }
//     dp[i, s] means The optimal solution for the first i numbers that are divided into s groups.
//    Updating dp[i, s] to dp[i, s + 1], we need to let the last group 
//    to include numbers from j + 1 to i, 
//    while the s groups divides the first j numbers (dp[j, s])
//    And try to find a j making Max(dp[j, s], S[i] - S[j]) smallest
//    => dp[i, s + 1] = Min( Max(dp[j, s], S[i] - S[j]) ),  s <= j < i
    for(int s = 1; s < m; s++) {
      for(int i = L; i > s; i--) {
        dp[i]=Integer.MAX_VALUE;
        for(int j = i - 1; j >= s; j--) {
          int t = Math.max(dp[j], S[i] - S[j]);
          if(t <= dp[i])
            dp[i] = t;
          else
            break;
        }
      }
    }
    return dp[L];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode410SplitArrayLargestSum one = new LeetCode410SplitArrayLargestSum();
    int[] nums = {7,2,5,10,8};
    int m = 3;
    System.out.println(one.splitArrayDP(nums, m));
    System.out.println(one.splitArrayDP2(nums, m));
  }

}
