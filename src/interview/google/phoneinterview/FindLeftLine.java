package interview.google.phoneinterview;

public class FindLeftLine {
  
  public int find(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return -1;
    }
    int rows = matrix.length;
    int columns = matrix[0].length;
    int row = 0;
    int column = 0;
    while (row < rows) {
      if (matrix[row][column] == 0 && ++column == columns) {
        return -1;
      } else if (matrix[row][column] == 1) {
        row++;
      }
    }
    return column;
  }

}
