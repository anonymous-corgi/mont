package algorithm.jiuzhang.c4.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0 (the number zero, one,
 * two).Zombies can turn the nearest people(up/down/left/right) into zombies every day, but can not
 * through wall. How long will it take to turn all people into zombies? Return -1 if can not turn
 * all people into zombies.
 *
 * Example Example 1:
 *
 * Input: [[0,1,2,0,0], [1,0,0,2,1], [0,1,0,0,0]] Output: 2 Example 2:
 *
 * Input: [[0,0,0], [0,0,0], [0,0,1]] Output: 4
 */
final class LintCode598ZombieInMatrix {

  interface Algorithm {

    int zombie(int[][] grid);
  }

  static final class Method implements Algorithm {

    @Override
    public int zombie(int[][] grid) {
      if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
      }

      int rowLength = grid.length;
      int colLength = grid[0].length;

      int count = 0;
      Queue<Integer> currTasks = new ArrayDeque<>();

      int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
      for (int i = 0; i < rowLength; i++) {
        for (int j = 0; j < colLength; j++) {
          if (grid[i][j] == 1) {
            currTasks.add(colLength * i + j);
          }
        }
      }

      while (!currTasks.isEmpty()) {
        count++;
        int queueSize = currTasks.size();
        for (int i = 0; i < queueSize; i++) {
          int pos = currTasks.poll();
          int currRow = pos / colLength;
          int currCol = pos % colLength;
          for (int k = 0; k < 4; k++) {
            int neighborRow = currRow + dir[k][0];
            int neighborCol = currCol + dir[k][1];
            if (neighborRow < rowLength && neighborRow > -1 && neighborCol < colLength
                && neighborCol > -1) {
              if (grid[neighborRow][neighborCol] == 0) {
                grid[neighborRow][neighborCol] = 1;
                currTasks.add(colLength * neighborRow + neighborCol);
              }
            }
          }
        }
      }

      for (int[] row : grid) {
        for (int j = 0; j < colLength; j++) {
          if (row[j] == 0) {
            return -1;
          }
        }
      }
      return --count;
    }
  }
}
