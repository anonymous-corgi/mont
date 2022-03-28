package algorithm.classic;

public class Matrix {

  private static class ZeroLengthException extends RuntimeException {
    ZeroLengthException() {
      super("The length of the matrix is zero.");
    }
  }

  private static class MatrixNotMatchException extends RuntimeException {
    MatrixNotMatchException() {
      super("Matrix a and b don't match..");
    }
  }

  private static class UnqualifiedMatrixException extends RuntimeException {
    UnqualifiedMatrixException(String matrix) {
      super(matrix + " is an unqualified matrix.");
    }
  }
  
  public static int[][] multiplyMatrix(int[][] a, int[][] b) {
    if (a == null || b == null) {
      throw new NullPointerException();
    }
    if (a.length == 0 || a[0].length == 0 || b.length == 0 || b[0].length == 0) {
      throw new ZeroLengthException();
    }
    final int LEN = b.length;
    final int ROW = a.length;
    final int COL = b[0].length;
    validateMatrix(a, "Matrix A");
    validateMatrix(b, "Matrix B");
    if (a[0].length != LEN) {
      throw new MatrixNotMatchException();
    }
    int[][] res = new int[ROW][COL];
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        for (int k = 0; k < LEN; k++) {
          res[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return res;
  }
  
  public static long[][] multiplyMatrix(long[][] a, long[][] b) {
    if (a == null || b == null) {
      throw new NullPointerException();
    }
    if (a.length == 0 || a[0].length == 0 || b.length == 0 || b[0].length == 0) {
      throw new ZeroLengthException();
    }
    final int LEN = b.length;
    final int ROW = a.length;
    final int COL = b[0].length;
    validateMatrix(a, "Matrix A");
    validateMatrix(b, "Matrix B");
    if (a[0].length != LEN) {
      throw new MatrixNotMatchException();
    }
    long[][] res = new long[ROW][COL];
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        for (int k = 0; k < LEN; k++) {
          res[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return res;
  }

  private static void validateMatrix(int[][] matrix, String name) {
    for (int[] m : matrix) {
      if (m.length != matrix[0].length) {
        throw new UnqualifiedMatrixException(name);
      }
    }
  }

  private static void validateMatrix(long[][] matrix, String name) {
    for (long[] m : matrix) {
      if (m.length != matrix[0].length) {
        throw new UnqualifiedMatrixException(name);
      }
    }
  }
}
