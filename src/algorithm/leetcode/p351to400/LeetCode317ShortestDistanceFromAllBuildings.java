package algorithm.leetcode.p351to400;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 317. Shortest Distance from All Buildings
 * Hard
 *
 * You want to build a house on an empty land
 * which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right.
 *
 * You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 *
 *
 * Example:
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 *
 * Note:
 * There will be at least one building.
 * If it is not possible to build such house according to the above rules, return -1.
 */
public class LeetCode317ShortestDistanceFromAllBuildings {

    private interface Method {
        int shortestDistance(int[][] grid);
    }

    private static final class BFS implements Method {

        private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        public int shortestDistance(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int rows = grid.length;
            int cols = grid[0].length;
            int[][] distance = new int[rows][cols];
            // count how many building each '0' can be reached
            int[][] reach = new int[rows][cols];
            int buildingNum = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        buildingNum++;
                        bfs(grid, i, j, reach, distance);
                    }
                }
            }
            int shortest = Integer.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                        shortest = Math.min(shortest, distance[i][j]);
                    }
                }
            }
            return shortest == Integer.MAX_VALUE ? -1 : shortest;
        }

        private void bfs(int[][] grid, int x, int y, int[][] reach, int[][] distance) {
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{x, y});
            int step = 1;
            while (!queue.isEmpty()) {
                for (int i = 0, size = queue.size(); i < size; i++) {
                    int[] cursor = queue.poll();
                    for (int[] dir : DIRS) {
                        int nextX = dir[0] + cursor[0];
                        int nextY = dir[1] + cursor[1];
                        if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                            continue;
                        }
                        if (grid[nextX][nextY] != 0 || visited[nextX][nextY]) {
                            continue;
                        }
                        //find next 0: level++;
                        distance[nextX][nextY] += step;
                        reach[nextX][nextY]++;
                        visited[nextX][nextY] = true;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
                step++;
            }
        }
    }
}