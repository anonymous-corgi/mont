package algorithm.leetcode.p851to900;

public class LeetCode900RLEIterator {
  
  class RLEIterator {
    
    private int[] A;
    private int index = 0;
    
    public RLEIterator(int[] A) {
      this.A = A;
    }
    
    public int next(int n) {
      if (index == A.length) {
        return -1;
      }
      while (index < A.length && n != 0) {
        if (n > A[index]) {
          n -= A[index];
          index += 2;
        } else {
          A[index] -= n;
          n = 0;
        }
      }
      return index == A.length ? -1 : A[index + 1];
    }
  }

}
