package jiuzhang.dp;

import java.util.List;

public class LintCode091MinimumAdjustmentCost {
  
  public int MinAdjustmentCost(List<Integer> A, int target) {
    // write your code here
    if (A == null || A.size() == 0) {
        return 0;
    }
    int aLen = A.size() + 1;
    int[][] dp = new int[aLen][101];
    for (int i = 1; i < aLen; i++) {
        for (int j = 0; j < 101; j++) {
            dp[i][j] = Integer.MAX_VALUE;
        }
    }
    for (int i = 1; i < aLen; i++) {
        for (int j = 0; j < 101; j++) {
            if (dp[i - 1][j] != Integer.MAX_VALUE) {
                int kStart = j - target;
                int kEnd = j + target;
                kStart = kStart < 0 ? 0 : kStart;
                kEnd = kEnd > 100 ? 100 : kEnd;
                for (int k = kStart; k <= kEnd; k++) {
                    int diff = dp[i - 1][j] + Math.abs(k - A.get(i - 1));
                    if (dp[i][k] > diff) {
                        dp[i][k] = diff;
                    }
                }
            }
        }
    }
    int res = Integer.MAX_VALUE;
    aLen--;
    for (int i = 0; i < 101; i++) {
        res = Math.min(res, dp[aLen][i]);
    }
    return res;
}
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
