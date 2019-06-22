package leetcode.p051to100;

public class LeetCode073SetMatrixZeroes {
  //This method is tricky: Uses the first row and the first column 
  //to record which row or column to be set zero.
  public void setZeroes(int[][] matrix) {
    if (matrix == null || matrix.length == 0
        || matrix[0] == null || matrix[0].length == 0) {
      return;
    }
    boolean firstColumn = false;
    boolean firstRow = false;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j <matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          if (j == 0) {
            firstColumn = true;
          }
          if (i == 0) {
            firstRow = true;
          }
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    //Watch out that i and j should start from 1, 
    //because they have already been set 0;
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j <matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }
    if (firstColumn) {
      for (int i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
      }
    }
    if (firstRow) {
      for (int i = 0; i < matrix[0].length; i++) {
        matrix[0][i] = 0;
      }
    }
  }

}
