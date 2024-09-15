package com.anonymouscorgi.karakoram.kb0250;

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
interface LeetCode265PaintHouseII {

  int minCostII(int[][] costs);

  LeetCode265PaintHouseII DP = new LeetCode265PaintHouseII() {

    @Override
    public int minCostII(int[][] costs) {
      // write your code here
      if (costs == null || costs.length == 0) {
        return 0;
      }
      if (costs[0] == null || costs[0].length == 0) {
        return 0;
      }
      int K = costs[0].length;

      int[] prevCost = new int[K];
      int[] currCost = new int[K];
      int minCostPrevHouse1 = 0;
      int minCostPrevHouse2 = 0;
      for (int i = 0; i < costs.length; i++) {
        int minCostCurrHouse1 = -1;
        int minCostCurrHouse2 = -1;
        for (int j = 0; j < K; j++) {
          if (j != minCostPrevHouse1) {
            currCost[j] = prevCost[minCostPrevHouse1] + costs[i][j];
          } else {
            currCost[j] = prevCost[minCostPrevHouse2] + costs[i][j];
          }

          if (minCostCurrHouse1 == -1 || currCost[j] < currCost[minCostCurrHouse1]) {
            minCostCurrHouse2 = minCostCurrHouse1;
            minCostCurrHouse1 = j;
          } else if (minCostCurrHouse2 == -1 || currCost[j] < currCost[minCostCurrHouse2]) {
            minCostCurrHouse2 = j;
          }
        }
        minCostPrevHouse1 = minCostCurrHouse1;
        minCostPrevHouse2 = minCostCurrHouse2;
        int[] temp = prevCost;
        prevCost = currCost;
        currCost = temp;
      }

      return prevCost[minCostPrevHouse1];
    }
  };
}
