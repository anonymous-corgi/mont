package com.anonymouscorgi.karakoram.kb0450;

interface LeetCode498DiagonalTraverse {

  int[] findDiagonalOrder(int[][] mat);

  LeetCode498DiagonalTraverse Method = new LeetCode498DiagonalTraverse() {

    private static int[][] DIRECTIONS = {{-1, 1}, {1, -1}};

    @Override
    public int[] findDiagonalOrder(int[][] mat) {
      int rows = mat.length;
      int cols = mat[0].length;
      int row = 0;
      int col = 0;
      int[] result = new int[rows * cols];
      result[0] = mat[0][0];
      int direction = 0;
      for (int index = 1; index < result.length; index++) {
        int nextRow = row + DIRECTIONS[direction][0];
        int nextCol = col + DIRECTIONS[direction][1];
        if (isOutBound(nextRow, nextCol, rows, cols)) {
          if (direction == 0) {
            nextRow = row;
            nextCol = col + 1;
            if (isOutBound(nextRow, nextCol, rows, cols)) {
              nextRow = row + 1;
              nextCol = col;
            }
          } else {
            nextRow = row + 1;
            nextCol = col;
            if (isOutBound(nextRow, nextCol, rows, cols)) {
              nextRow = row;
              nextCol = col + 1;
            }
          }
          direction = (direction + 1) % 2;
        }
        row = nextRow;
        col = nextCol;
        result[index] = mat[nextRow][nextCol];
      }

      return result;
    }

    private boolean isOutBound(int nextRow, int nextCol, int rows, int cols) {
      return nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols;
    }
  };
}
