package com.anonymouscorgi.karakoram.jiuzhang.dp;

/**
 * There are a row of n houses, each house can be  painted with one of the k colors. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that
 * no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For
 * example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of
 * painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 *
 * Note: All costs are positive integers.
 *
 * Example:
 *
 * Input: [[1,5,3],[2,9,4]] Output: 5 Explanation: Paint house 0 into color 0, paint house 1 into
 * color 2. Minimum cost: 1 + 4 = 5; Or paint house 0 into color 2, paint house 1 into color 0.
 * Minimum cost: 3 + 2 = 5.
 */
public class LeetCode265PaintHouseII {

  public int minCostII(int[][] costs) {
    // write your code here
    if (costs == null || costs.length == 0) {
      return 0;
    }
    if (costs[0] == null || costs[0].length == 0) {
      return 0;
    }
    int K = costs[0].length;

    int[] pMin = new int[K];
    int[] cMin = new int[K];
    int i1 = 0;
    int i2 = 0;
    for (int i = 0, len = costs.length; i < len; i++) {
      int m1 = -1;
      int m2 = -1;
      for (int j = 0; j < K; j++) {
        if (j != i1) {
          cMin[j] = pMin[i1] + costs[i][j];
        } else {
          cMin[j] = pMin[i2] + costs[i][j];
        }

        if (m1 == -1 || cMin[j] < cMin[m1]) {
          m2 = m1;
          m1 = j;
        } else if (m2 == -1 || cMin[j] < cMin[m2]) {
          m2 = j;
        }
      }
      i1 = m1;
      i2 = m2;
      int[] temp = pMin;
      pMin = cMin;
      cMin = temp;
    }

    return pMin[i1];
  }
}
