package com.anonymouscorgi.karakoram.kb0600;

import com.anonymouscorgi.karakoram.base.Point;
import java.util.ArrayDeque;
import java.util.Queue;

final class LintCode611KnightShortestPath {

  interface Algorithm {

    int shortestPath(boolean[][] grid, Point source, Point destination);
  }

  static Algorithm Method = new Algorithm() {

    @Override
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
      if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return -1;
      }

      int rowLength = grid.length;
      int colLength = grid[0].length;

      if (!insideMargin(source.x, source.y, rowLength, colLength) || !insideMargin(destination.x,
          destination.y, rowLength, colLength)) {
        return -1;
      }

      int count = 0;
      Queue<Point> taskList = new ArrayDeque<>();
      taskList.offer(source);

      int[][] movement = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};

      while (!taskList.isEmpty()) {
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
          Point current = taskList.poll();
          if (current.x == destination.x && current.y == destination.y) {
            return count;
          }
          for (int k = 0; k < 8; k++) {
            int newX = current.x + movement[k][0];
            int newY = current.y + movement[k][1];
            if (!insideMargin(newX, newY, rowLength, colLength)) {
              continue;
            }
            if (grid[newX][newY]) {
              continue;
            }
            taskList.offer(new Point(newX, newY));
            grid[newX][newY] = true;
          }
        }
        count++;
      }
      return -1;
    }

    private boolean insideMargin(int row, int col, int rowLength, int colLength) {
      return row >= 0 && row < rowLength && col >= 0 && col < colLength;
    }
  };
}
