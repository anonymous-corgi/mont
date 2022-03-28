package algorithm.leetcode.p301to350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 305. Number of Islands II
 * Hard
 *
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 * Example:
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 *
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 *
 *
 * Follow up:
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class LeetCode305NumberOfIslandsII {

    private interface Method {
        List<Integer> numIslands2(int m, int n, int[][] positions);
    }

    private static final class UnionFind implements Method {

        private final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int[] ufp = new int[m * n];
            List<Integer> res = new ArrayList<>(positions.length);
            Arrays.fill(ufp, -1);
            int islandCount = 0;
            for (int[] position : positions) {
                int curIndex = position[0] * n + position[1];
                if (ufp[curIndex] != -1) {
                    res.add(islandCount);
                    continue;
                }
                islandCount++;
                ufp[curIndex] = curIndex;
                for (int[] dir : DIRS) {
                    int r = position[0] + dir[0];
                    int c = position[1] + dir[1];
                    int nearbyIndex = r * n + c;
                    if (0 <= r && r < m && 0 <= c && c < n && ufp[nearbyIndex] != -1) {
                        if (connect(ufp, curIndex, nearbyIndex)) {
                            islandCount--;
                        }
                    }
                }
                res.add(islandCount);
            }
            return res;
        }

        private int find(int[] ufp, int x) {
            return ufp[x] == x ? x : (ufp[x] = find(ufp, ufp[x]));
        }

        private boolean connect(int[] ufp, int x, int y) {
            int rootX = find(ufp, x);
            int rootY = find(ufp, y);
            if (rootX != rootY) {
                if (rootX < rootY) {
                    ufp[rootY] = rootX;
                } else {
                    ufp[rootX] = rootY;
                }
                return true;
            }
            return false;
        }
    }
}
