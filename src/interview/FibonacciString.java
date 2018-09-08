package interview;

import static org.junit.Assert.*;
import org.junit.Test;

public class FibonacciString {
  
  public static class DFS_method {
    
    public boolean isFibonacciString(String s) {
      return helper(s.toCharArray(), 1, 0, 0, 0);
    }
    
    private boolean helper(char[] chs, int n, int start, int val1, int val2) {
      if (start == chs.length) {
        return n > 3;
      }
      
      for (int i = start + 1; i <= chs.length; i++) {      
        long val = Long.parseLong(String.valueOf(chs, start, i - start));
        if (n > 2 && (val > val1 + val2 || val > Integer.MAX_VALUE) ) {
          break;
        }
        if (n > 2 && val != val1 + val2) {
          continue;
        }
        if (helper(chs, n + 1, i, val2, (int) val)) {
          return true;
        }
      }
      return false;
    }
    
  }
  
  public static class NonRecursive_method {
    
    public boolean isFibonacciString(String s) {
      
      for (int i = 1, iLen = s.length() / 2; i <= iLen; i++) {
        long val1 = Long.parseLong(s.substring(0, i));
        if (val1 > Integer.MAX_VALUE) { break; }
        for (int j = i + 1, jLen = s.length(); j <= jLen; j++) {
          long val2 = Long.parseLong(s.substring(i, j));
          if (val2 > Integer.MAX_VALUE) { break; }
          int n = j;
          for (int m = j + 1; m <= jLen; m++) {
            long val = Long.parseLong(s.substring(n, m));
            if (val > Integer.MAX_VALUE) { break; }
            if (val > val1 + val2) {
              break;
            }
            if (val == val1 + val2) {
              if (m == jLen) {
                return true;
              }
              n = m;
              val1 = val2;
              val2 = val;
            }
          }
        }
      }
      return false;
    }
    
  }
  
  @Test 
  public void testIsFibonacciString() {
    FibonacciString.DFS_method dfs = 
        new FibonacciString.DFS_method();    
    FibonacciString.NonRecursive_method non = 
        new FibonacciString.NonRecursive_method();
    
    String[] ss = {"112358", "1123581321345589144", "10013113126239", 
        "11", "12345", "1123581321345589143"};
    
    boolean[] ex = {true, true, true, false, false, false};
    for (int i = 0; i < ss.length; i++) {
      assertEquals(ex[i], dfs.isFibonacciString(ss[i]));
      assertEquals(ex[i], non.isFibonacciString(ss[i]));
    }
  }

}
