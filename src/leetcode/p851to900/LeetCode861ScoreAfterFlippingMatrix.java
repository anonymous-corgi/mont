package leetcode.p851to900;

public class LeetCode861ScoreAfterFlippingMatrix {
  
  public int matrixScore(int[][] A) {
    int rows = A.length;
    int cols = A[0].length;
    int flag = 1 << (cols - 1);
    int res = flag * rows;
    
    for (int i = 0; i < rows; i++) {
      if (A[i][0] == 0) {
        toggleRow(A[i]);
      }
    }
    
    for (int i = 1; i < cols; i++) {
      flag >>= 1;
      int count = 0;
      for (int j =  0; j < rows; j++) {
        count += A[j][i];
      }
      if (count * 2 < rows) {
        toggleCol(A, i);
        count = rows - count;
      }
      res += flag * count;
    }
    
    return res;
  }
  
  private void toggleRow(int[] row) {
    for (int i = 0; i < row.length; i++) {
      row[i] ^= 1;
    }
  }
  
  private void toggleCol(int[][] nums, int col) {
    for (int i = 0; i < nums.length; i++) {
      nums[i][col] ^= 1;
    }
  }

  public static void main(String[] args) {
    int[][] A = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
    LeetCode861ScoreAfterFlippingMatrix one =
        new LeetCode861ScoreAfterFlippingMatrix();
    System.out.println(one.matrixScore(A));
  }

}
