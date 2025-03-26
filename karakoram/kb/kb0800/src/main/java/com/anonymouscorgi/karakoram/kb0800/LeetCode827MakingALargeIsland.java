package com.anonymouscorgi.karakoram.kb0800;

import java.util.HashMap;
import java.util.HashSet;

interface LeetCode827MakingALargeIsland {

  int largestIsland(int[][] grid);

  LeetCode827MakingALargeIsland Method = new LeetCode827MakingALargeIsland() {
    @Override
    public int largestIsland(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;
      int[] ufp = new int[rows * cols];
      int[] sizes = new int[rows * cols];

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          int num = cols * row + col;
          ufp[num] = num;
          sizes[num] = grid[row][col];
        }
      }

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          if (grid[row][col] == 0) {
            continue;
          }
          int num = cols * row + col;
          if (col + 1 < cols && grid[row][col + 1] == 1) {
            connect(ufp, sizes, num, num + 1);
          }
          if (row + 1 < rows && grid[row + 1][col] == 1) {
            connect(ufp, sizes, num, num + cols);
          }
        }
      }

      int maxSize = 0;
      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          int num = cols * row + col;
          if (grid[row][col] == 1) {
            maxSize = Math.max(maxSize, sizes[num]);
          } else {
            HashSet<Integer> islands = new HashSet<>();
            if (row - 1 >= 0 && grid[row - 1][col] == 1) {
              islands.add(find(ufp, num - cols));
            }
            if (col + 1 < cols && grid[row][col + 1] == 1) {
              islands.add(find(ufp, num + 1));
            }
            if (row + 1 < rows && grid[row + 1][col] == 1) {
              islands.add(find(ufp, num + cols));
            }
            if (col - 1 >= 0 && grid[row][col - 1] == 1) {
              islands.add(find(ufp, num - 1));
            }
            int curSize = 1;
            for (Integer island : islands) {
              curSize += sizes[island];
            }
            maxSize = Math.max(maxSize, curSize);
          }
        }
      }

      return maxSize;
    }

    private void connect(int[] ufp, int[] sizes, int a, int b) {
      int rootA = find(ufp, a);
      int rootB = find(ufp, b);
      if (rootA < rootB) {
        ufp[rootB] = rootA;
        sizes[rootA] += sizes[rootB];
        sizes[rootB] = 0;
      } else if (rootB < rootA) {
        ufp[rootA] = rootB;
        sizes[rootB] += sizes[rootA];
        sizes[rootA] = 0;
      }
    }

    private int find(int[] ufp, int num) {
      return ufp[num] == num ? num : (ufp[num] = find(ufp, ufp[num]));
    }
  };
}
