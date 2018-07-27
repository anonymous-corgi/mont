package jiuzhang.dp.longestincreasingsubarray;

public class LeetCode674LongestContinuousIncreasingSubsequence {
  
  public int longestIncreasingContinuousSubsequence(int[] A) {
    // write your code here
    if (A.length == 0) {
      return 0;
    }
    
    int longest = 1;
    int[] dp = new int[A.length];
    dp[0] = 1;
    
    for (int i = 1; i < A.length; i++) {
      dp[i] = A[i - 1] > A[i] ? dp[i - 1] + 1 : 1;
      longest = Math.max(longest, dp[i]);
    }
    
    for (int i = 1; i < A.length; i++) {
      dp[i] = A[i - 1] < A[i] ? dp[i - 1] + 1 : 1;
      longest = Math.max(longest, dp[i]);
    }
    
    return longest;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode674LongestContinuousIncreasingSubsequence one = 
        new LeetCode674LongestContinuousIncreasingSubsequence();
    int[] A = {5,2,3,4,6,8,8,8,4,5,6,4,3,2,1};
    System.out.println(one.longestIncreasingContinuousSubsequence(A));
  }

}
