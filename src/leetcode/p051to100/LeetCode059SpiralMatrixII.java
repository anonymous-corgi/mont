package leetcode.p051to100;

import utils.Print;

public class LeetCode059SpiralMatrixII {
  
  public int[][] generateMatrix(int n) {
    if (n < 1) {
      return new int[0][0];
    }
    int[][] result = new int[n][n];
    int left = 0;
    int top = 0;
    int bottom = n - 1;
    int right = n - 1;
    int index = 1;
    while (left <= right && top <= bottom) {
      for (int j = left; j <= right; j++) {
        result[top][j] = index++;
      }
      top++;
      for (int i = top; i <= bottom; i++) {
        result[i][right] = index++;
      }
      right--;
      for (int j = right; j >= left; j--) {
        result[bottom][j] = index++;
      }
      bottom--;
      for (int i = bottom; i >= top; i--) {
        result[i][left] = index++;
      }
      left++;
    }
    return result;
  }

  public static void main(String[] args) {
    LeetCode059SpiralMatrixII one =
        new LeetCode059SpiralMatrixII();
    int n = 4;
    int[][] res = one.generateMatrix(n);
    for (int i = 0; i < res.length; i++) {
      Print.printArray(res[i]);
    }
  }

}
