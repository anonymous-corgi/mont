package algorithm.classic;

import static org.junit.Assert.*;
import org.junit.Test;

public class Fibonacci {
  //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233
  interface FibonacciNumber {
    long getFibonacciNumber(int n);
  }

  public static class Recursive_method implements FibonacciNumber {
    
    @Override
    public long getFibonacciNumber(int n) {
      long[] f = new long[]{0, 1, 1};
      
      if (n < 2) {
        return f[n];
      }
      
      for(int i = 2; i < n; i++) {
        f[0] = f[1];
        f[1] = f[2];
        f[2] = f[0] + f[1];
      }
      
      return f[2];
    }
    
  }

  
  public static class Matrix_method implements FibonacciNumber {

    //|f[n]   | = |1, 1| * |f[n - 1]|  => |1, 1|^(n - 1) * |f[1]|
    //|f[n -1]|   |1, 0|   |f[n - 2]|     |1, 0|           |f[0]|
    
    private final long[][] baseMatrixQ = new long[][]{{1, 1}, {1, 0}};
    private final long[] init = new long[]{0, 1};

    @Override
    public long getFibonacciNumber(int n) {
      if (n < 2) {
        return init[n];
      }
      long[][] matrixQ = getMatrixQ(n - 1, baseMatrixQ);
      
      return matrixQ[0][0];
    }
    
    private long[][] getMatrixQ(int n, long[][] matrix) {
      if (n == 1) {
        return matrix;
      }
      long[][] neoMatrixQ = getMatrixQ(n / 2,  matrix);
      return n % 2 == 0 ? Matrix.multiplyMatrix(neoMatrixQ, neoMatrixQ):
                       Matrix.multiplyMatrix(matrix, Matrix.multiplyMatrix(neoMatrixQ, neoMatrixQ));
    }
    
  }
  
  private FibonacciNumber getTestInstance() {
//    return new Recursive_method();
    return new Matrix_method();
  }
  
  public long getFibonacciNumber(int n) {
    return getTestInstance().getFibonacciNumber(n);
  }
  
  
  @Test
  public void testGetFibonacciNumber1() {
    int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    long[] res = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], getFibonacciNumber(num[i]));
    }
  }

  public static void main(String[] args) {
    int n = 3;
    System.out.println(new Fibonacci().getFibonacciNumber(n));
  }

}
