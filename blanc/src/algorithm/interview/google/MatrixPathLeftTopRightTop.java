package algorithm.interview.google;

public class MatrixPathLeftTopRightTop {
  
  public int findPath(int n, int m) {
    int[][] dp = new int[n][m];
    for (int i = 0; i < m && i < n; i++) {
      dp[i][i] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int row = j;
        int col = i + j;
        if (col + row >= m) {
          break;
        }
        for (int d = -1; d < 2; d++) {
          if (row + d >= 0 && row + d < n) {
            dp[row][col] += dp[row + d][col - 1];
          }
        }
      }
    }
    return dp[0][m - 1];
  }

  public static void main(String[] args) {
    MatrixPathLeftTopRightTop one = new MatrixPathLeftTopRightTop();
    int n = 6;
    int m = 6;
    System.out.println(one.findPath(n, m));
  }

}
