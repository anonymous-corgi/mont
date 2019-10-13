package algorithm.leetcode.p251to300;

/**
 * 256. Paint House
 * Easy
 * <p>
 * There are a row of n houses, each house can be painted with one of the three colors:
 * red, blue or green. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green,
 * and so on... Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Example:
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 */
public class LeetCode256PaintHouse {

    private interface Method {
        int minCost(int[][] costs);
    }

    private static final class DP implements Method {

        @Override
        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0) {
                return 0;
            }
            int[] curMax = new int[3];
            int[] preMax = new int[3];
            for (int[] cost : costs) {
                int[] temp = curMax;
                curMax = preMax;
                preMax = temp;
                curMax[0] = cost[0] + Math.min(preMax[1], preMax[2]);
                curMax[1] = cost[1] + Math.min(preMax[2], preMax[0]);
                curMax[2] = cost[2] + Math.min(preMax[0], preMax[1]);
            }
            return Math.min(curMax[0], Math.min(curMax[1], curMax[2]));
        }
    }
}
