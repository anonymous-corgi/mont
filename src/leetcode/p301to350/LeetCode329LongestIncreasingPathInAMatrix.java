package leetcode.p301to350;

public class LeetCode329LongestIncreasingPathInAMatrix {
  
  public static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int rows;
  private int columns;
  private int[][] cache;
  public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0
        || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    rows = matrix.length;
    columns = matrix[0].length;
    cache = new int[rows][columns];
    int max = 1;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int length = search(matrix, i, j);
        max = Math.max(max, length);
      }
    }
    return max;
  }
  
  private int search(int[][] matrix, int row, int column) {
    if (cache[row][column] != 0) {
      return cache[row][column];
    }
    cache[row][column] = 1;
    for (int[] dir : DIRS) {
      int nRow = row + dir[0];
      int nColumn = column + dir[1];
      if (nRow >= 0 && nRow < rows && nColumn >= 0 && nColumn < columns 
          && matrix[row][column] > matrix[nRow][nColumn]) {
        int len = search(matrix, nRow, nColumn) + 1;
        cache[row][column] = Math.max(cache[row][column], len);
      }
    }
    return cache[row][column];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode329LongestIncreasingPathInAMatrix one = new LeetCode329LongestIncreasingPathInAMatrix();
    int[][] matrix = {{1,2,3,4,5},{16,17,18,19,6},{15,24,25,20,7},{14,23,22,21,8},{13,12,11,10,9}};
    System.out.println(one.longestIncreasingPath(matrix));
  }

}
