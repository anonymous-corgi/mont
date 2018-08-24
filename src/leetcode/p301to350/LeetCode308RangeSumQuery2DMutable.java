package leetcode.p301to350;

public class LeetCode308RangeSumQuery2DMutable {
  
  private int rows, cols;
  private final int[][] stub;
  private final int[][] bit;
  
  public LeetCode308RangeSumQuery2DMutable(int[][] matrix) {
      rows = matrix.length;
      cols = matrix[0].length;
      stub = new int[rows][cols];
      bit = new int[rows + 1][cols + 1];
      
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
              update(i, j, matrix[i][j]);
          }
      }
  }
  
  public void update(int row, int col, int val) {
      int delta = val - stub[row][col];
      stub[row][col] = val;
      
      for (int i = row + 1; i <= rows; i = i + lowbit(i)) {
          for (int j = col + 1; j <= cols; j = j + lowbit(j)) {
              bit[i][j] += delta;
          }
      }
  }
  
  public int sumRegion(int row1, int col1, int row2, int col2) {
      return (
          prefixSum(row2, col2) -
          prefixSum(row2, col1 - 1) -
          prefixSum(row1 - 1, col2) +
          prefixSum(row1 - 1, col1 - 1)
      );
  }
  
  private int prefixSum(int row, int col) {
      int sum = 0;
      for (int i = row + 1; i > 0; i = i - lowbit(i)) {
          for (int j = col + 1; j > 0; j = j - lowbit(j)) {
              sum += bit[i][j];
          }
      }
      return sum;
  }
  
  private int lowbit(int x) {
      return x & -x;
  }

  public static void main(String[] args) {
    
  }

}
