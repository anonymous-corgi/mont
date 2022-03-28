package algorithm.interview.visa;

import static org.junit.Assert.*;
import org.junit.Test;

public class RemovingChocolates {
  
  interface RemovingChocolatesMethod {
    int numberOfWays(int n);
  }
  
  public static class Recursive_method implements RemovingChocolatesMethod{
    
    @Override
    public int numberOfWays(int n) {
      int[] cache = new int[]{0, 1, 1, 2};
      if (n <= 3) {
        return cache[n];
      }
      for (int i = 4; i <= n; i++) {
        move(cache);
        cache[3] = cache[0] + cache[2];
      }
      return cache[3];
    }
    
    private void move(int[] cache) {
      cache[0] = cache[1];
      cache[1] = cache[2];
      cache[2] = cache[3];
    }
    
  }
  
  public static class Recursive_method2 implements RemovingChocolatesMethod{
    
    private static final int MOD = (int) 1e9 + 7;
    
    @Override
    public int numberOfWays(int n) {
      int[] f = new int[]{1, 1, 2};
      for (int i = 4; i <= n; i += 3) {
        f[0] = (f[0] + f[2]) % MOD;
        f[1] = (f[1] + f[0]) % MOD;
        f[2] = (f[1] + f[2]) % MOD;
      }
      return f[(n - 1) % 3];
    }
    
  }
  
  public static class Matrix_method implements RemovingChocolatesMethod{
    
    private final long MOD = (long) (1e9 + 9); 
    
    @Override
    public int numberOfWays(int n) {
      long[] init = new long[]{1, 1, 2};
      if (n < 4) {
        return (int) init[n - 1];
      }
      long[][] baseQ = {{1, 0, 1}, {1, 0, 0}, {0, 1, 0}};
      long[][] matrixQ = getMatrixQ(n - 3, baseQ);
      
      return (int) (matrixQ[0][0] * 2 + matrixQ[0][1] + matrixQ[0][2]);
    }
    
    private long[][] getMatrixQ(int n, long[][] matrix) {
      if (n == 1) {
        return matrix;
      }
      long[][] neoMatrixQ = getMatrixQ(n / 2,  matrix);
      return n % 2 == 1 ? multiplyMatrix(matrix, multiplyMatrix(neoMatrixQ, neoMatrixQ)) :
        multiplyMatrix(neoMatrixQ, neoMatrixQ);
    }
    
    private long[][] multiplyMatrix(long[][] a, long[][] b) {
      final int LEN = a.length;
      long[][] temp = new long[LEN][LEN];
      for (int i = 0; i < LEN; i++) {
        for (int j = 0; j < LEN; j++) {
          for (int k = 0; k < LEN; k++) {
            temp[i][j] += a[i][k] * b[k][j] % MOD;
          }
        }
      }
      return temp;
    }
    
  }
  
  private RemovingChocolatesMethod getInstance() {
//    return new Recursive_method();
    return new Recursive_method2();
//    return new Matrix_method();
  }
  
  public int findRemovingWays(int n) {
    return getInstance().numberOfWays(n);
  }
  
  
  @Test
  public void testFindRemovingWays1() {
    int[] n = {1, 2, 3, 4, 5, 6, 7};
    int[] res = {1, 1, 2, 3, 4, 6, 9}; 
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], findRemovingWays(n[i]));
    }
  }  
  
  @Test
  public void testFindRemovingWays2() {
    int n = 7;
    int res = 9;
    assertEquals(res, findRemovingWays(n));
  }
  
//  @Test
//  public void testFindRemovingWays() {
//    int n = ;
//    int res = ;
//    assertEquals(res, findRemovingWays(n));
//  }
  
  
  public static void main(String[] args) {
    int n = 4;
    RemovingChocolates one = new RemovingChocolates();
    System.out.println(one.findRemovingWays(n));
  }
  
}
