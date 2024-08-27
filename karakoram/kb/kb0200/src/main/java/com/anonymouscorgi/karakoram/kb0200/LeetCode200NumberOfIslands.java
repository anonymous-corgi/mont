package com.anonymouscorgi.karakoram.kb0200;

import java.util.Arrays;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return
 * the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input: grid = [ ["1","1","1","1","0"], ["1","1","0","1","0"], ["1","1","0","0","0"],
 * ["0","0","0","0","0"] ] Output: 1 Example 2:
 *
 * Input: grid = [ ["1","1","0","0","0"], ["1","1","0","0","0"], ["0","0","1","0","0"],
 * ["0","0","0","1","1"] ] Output: 3 Constraints:
 *
 * m == grid.length n == grid[i].length 1 <= m, n <= 300 grid[i][j] is '0' or '1'.
 */
final class LeetCode200NumberOfIslands {

  interface Algorithm {

    int numIslands(boolean[][] grid);
  }

  static final class UnionFind implements Algorithm {


    @Override
    public int numIslands(boolean[][] grid) {
      // write your code here
      if (grid == null || grid.length == 0
          || grid[0] == null || grid[0].length == 0) {
        return 0;
      }

      int rows = grid.length;
      int columns = grid[0].length;
      int size = rows * columns;
      int[] ufp = new int[size];
      Arrays.setAll(ufp, i -> i);

      int count = 0;
      int current = -1;
      for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < columns; ++j) {
          current++;
          if (grid[i][j]) {
            count++;
            if (j + 1 < columns && grid[i][j + 1]
                && connect(ufp, (current + 1), current)) {
              count--;
            }
            if (i + 1 < rows && grid[i + 1][j]
                && connect(ufp, (current + columns), current)) {
              count--;
            }
          }
        }
      }
      return count;
    }

    private boolean connect(int[] ufp, int from, int to) {
      int root_to = find(ufp, to);
      int root_from = find(ufp, from);
      if (root_from != root_to) {
        ufp[root_from] = root_to;
        return true;
      } else {
        return false;
      }
    }

    private int find(int[] ufp, int num) {
      return ufp[num] == num ? num : (ufp[num] = find(ufp, ufp[num]));
    }
  }
}
