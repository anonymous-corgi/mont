package com.anonymouscorgi.karakoram.kb1050;

import com.anonymouscorgi.karakoram.annotation.Accepted;
import java.util.ArrayDeque;

/**
 * <h3>LeetCode 1091. Shortest Path in Binary Matrix
 * <p>
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If
 * there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the
 * bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * <li>All the visited cells of the path are 0.
 * <li>All the adjacent cells of the path are 8-directionally connected (i.e., they are different
 * and they share an edge or a corner).
 * <li>The length of a clear path is the number of visited cells of this path.
 */
interface LeetCode1091ShortestPathInBinaryMatrix {

  int shortestPathBinaryMatrix(int[][] grid);

  @Accepted
  LeetCode1091ShortestPathInBinaryMatrix Method = new LeetCode1091ShortestPathInBinaryMatrix() {

    private static int[][] DIRECTIONS = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1},
        {-1, 0}};

    @Override
    public int shortestPathBinaryMatrix(int[][] grid) {
      if (grid[0][0] != 0) {
        return -1;
      }
      int steps = 1;
      int rows = grid.length;
      int cols = grid[0].length;
      int[][] shortest = new int[grid.length][grid[0].length];
      shortest[0][0] = 1;
      ArrayDeque<int[]> tasks = new ArrayDeque<>();
      tasks.offer(new int[]{0, 0});
      while (!tasks.isEmpty()) {
        steps++;
        for (int i = 0, taskSize = tasks.size(); i < taskSize; i++) {
          int[] point = tasks.poll();
          for (int[] direction : DIRECTIONS) {
            int[] nextPoint = {point[0] + direction[0], point[1] + direction[1]};
            if (isWithinRange(nextPoint, rows, cols)
                && grid[nextPoint[0]][nextPoint[1]] == 0
                && shortest[nextPoint[0]][nextPoint[1]] == 0) {
              shortest[nextPoint[0]][nextPoint[1]] = steps;
              tasks.offer(nextPoint);
            }
          }
        }
      }

      return shortest[rows - 1][cols - 1] != 0 ? shortest[rows - 1][cols - 1] : -1;
    }

    private boolean isWithinRange(int[] point, int maxRows, int maxCols) {
      return 0 <= point[0] && point[0] < maxRows && 0 <= point[1] && point[1] < maxCols;
    }
  };
}
