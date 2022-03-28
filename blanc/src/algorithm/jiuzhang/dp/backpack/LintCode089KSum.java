package algorithm.jiuzhang.dp.backpack;

/**
 * Description
 * Given n distinct positive integers, integer k (k <= n) and a number target.
 * <p>
 * Find k numbers where sum is target. Calculate how many solutions there are?
 * <p>
 * Have you met this question in a real algorithm.interview?  Yes
 * Example
 * Given [1,2,3,4], k = 2, target = 5.
 * <p>
 * There are 2 solutions: [1,4] and [2,3].
 * <p>
 * Return 2.
 */
public class LintCode089KSum {

    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0 || target == 0) {
            return 0;
        }
        int[][] dp = new int[k + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > target) {
                continue;
            }
            int mEnd = i + 1 >= k ? k : i + 1;
            for (int m = mEnd; m > 0; m--) {
                for (int n = target; n >= A[i]; n--) {
                    dp[m][n] += dp[m - 1][n - A[i]];
                }
            }
        }
        return dp[k][target];
    }
}
