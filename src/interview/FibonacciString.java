package interview;

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
        int val = Integer.parseInt(String.valueOf(chs, start, i - start));
        if (n > 2 && val > val1 + val2) {
          break;
        }
        if (n > 2 && val != val1 + val2) {
          continue;
        }
        if (helper(chs, n + 1, i, val2, val)) {
          return true;
        }
      }
      return false;
    }
    
  }
  
  public static class NonRecursive_method {
    
    public boolean isFibonacciString(String s) {
      
      for (int i = 1, iLen = s.length() / 2; i <= iLen; i++) {
        int val1 = Integer.parseInt(s.substring(0, i));
        for (int j = i + 1, jLen = s.length(); j <= jLen; j++) {
          int val2 = Integer.parseInt(s.substring(i, j));
          int n = j;
          for (int m = j + 1; m <= jLen; m++) {
            int val = Integer.parseInt(s.substring(n, m));            
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
  
  
  public static void main(String[] args) {
    String s = "111223";
    FibonacciString.DFS_method one = 
        new FibonacciString.DFS_method();    
    FibonacciString.NonRecursive_method two = 
        new FibonacciString.NonRecursive_method();
    System.out.println(one.isFibonacciString(s));
    System.out.println(two.isFibonacciString(s));
  }
  
}
