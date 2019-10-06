package leetcode.p1051to1100;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 1066. Campus Bikes II
 * Medium
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes,
 * with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * We assign one unique bike to each worker so that
 * the sum of the Manhattan distances between each worker and their assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2
 * is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 *
 * Example 1:
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: 6
 * Explanation:
 * We assign bike 0 to worker 0, bike 1 to worker 1.
 * The Manhattan distance of both assignments is 3, so the output is 6.
 *
 *
 * Example 2:
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: 4
 * Explanation:
 * We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2,
 * bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
 *
 *
 * Note:
 * 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 10
 */
public class LeetCode1066CampusBikesII {

    private interface Method {
        int assignBikes(int[][] workers, int[][] bikes);
    }

    private static final class DP implements Method {

        public int assignBikes(int[][] workers, int[][] bikes) {
            int workerNum = workers.length;
            int bikeNum = bikes.length;
            int[][] dp = new int[workerNum + 1][1 << bikeNum];
            for (int[] d : dp) {
                Arrays.fill(d, Integer.MAX_VALUE / 2);
            }
            dp[0][0] = 0;
            int min = Integer.MAX_VALUE;
            for (int w = 1; w <= workerNum; w++) {
                for (int b = 0; b < bikeNum; b++) {
                    for (int s = 1; s < dp[0].length; s++) {
                        if ((s & (1 << b)) == 0) {
                            continue;
                        }
                        int prev = s ^ (1 << b);
                        dp[w][s] = Math.min(dp[w][s], dp[w - 1][prev] + dis(workers[w - 1], bikes[b]));
                        if (w == workerNum) {
                            min = Math.min(min, dp[w][s]);
                        }
                    }
                }
            }
            return min;
        }

        private int dis(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }

    private static final class DFS implements Method {

        public int assignBikes(int[][] workers, int[][] bikes) {
            int[] result = new int[]{Integer.MAX_VALUE};
            Map<Integer, Integer>[] cache = new Map[workers.length];
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new HashMap<>();
            }
            dfs(workers, bikes, 0, 0, cache);
            return result[0];
        }

        private int dfs(int[][] workers, int[][] bikes, int w, int bikeUsed, Map<Integer, Integer>[] cache) {
            if (w == workers.length) {
                return 0;
            } else if (cache[w].containsKey(bikeUsed)) {
                return cache[w].get(bikeUsed);
            }

            int res = Integer.MAX_VALUE;
            for (int b = 0; b < bikes.length; b++) {
                int bBit = 1 << b;
                if ((bikeUsed & bBit) != 0) {
                    continue;
                }
                int dis = Math.abs(workers[w][0] - bikes[b][0]) + Math.abs(workers[w][1] - bikes[b][1]);
                bikeUsed ^= bBit;
                int sub = dfs(workers, bikes, w + 1, bikeUsed, cache);
                res = Math.min(res, dis + sub);
                bikeUsed ^= bBit;
            }
            cache[w].put(bikeUsed, res);
            return res;
        }
    }
}