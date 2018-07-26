package jiuzhang.dp.backpack;

/**
 * 
 * Description
 * Given n distinct positive integers, integer k (k <= n) and a number target.
 * 
 * Find k numbers where sum is target. Calculate how many solutions there are?
 * 
 * Have you met this question in a real interview?  Yes
 * Example
 * Given [1,2,3,4], k = 2, target = 5.
 * 
 * There are 2 solutions: [1,4] and [2,3].
 * 
 * Return 2.
 */
public class LintCode089KSum {
  public int kSum(int[] A, int k, int target) {
    // write your code here
    if (A == null || A.length == 0) {
      return 0;
    }
    if (target == 0) {
      return 0;
    }
    int aLen = A.length;
    int[][] dp = new int[k + 1][target + 1];
    //Because all A[] are >= 0, and at least have 1 item,
    //It doesn't exist situation where the sum is 0.
    //There is no need to set dp[i][0] to 1.
    for (int i = 0; i < aLen; i++) {
      if (A[i] > target) {
        continue;
      }
      int mEnd = i + 1 > k ? k : i + 1;
      for (int m = mEnd; m >= 1; m--) {
        for (int n = target; n >= A[i]; n--) {
          dp[m][n] += dp[m - 1][n - A[i]];
        }
      }
      dp[1][A[i]]++; 
    }
    return dp[k][target];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LintCode089KSum one = new LintCode089KSum();
    int[] A = {1, 2, 3, 4};
    int k = 2;
    int target = 5;
    System.out.println(one.kSum(A, k, target));
  }

}
