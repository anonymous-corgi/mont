package leetcode.p101to150;

import java.util.List;

public class LeetCode120Triangle {

  public int minimumTotal(List<List<Integer>> triangle) {
    int LEN = triangle.size();
    int[] dp = new int[LEN + 1];
    for (int i = LEN - 1; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
      }
    }
    return dp[0];
  }

}
