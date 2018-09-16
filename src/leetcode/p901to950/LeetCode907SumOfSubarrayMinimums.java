package leetcode.p901to950;

import static org.junit.Assert.*;
import java.util.Stack;
import org.junit.Test;

public class LeetCode907SumOfSubarrayMinimums {
  
  private final int MOD = (int) (1e9 + 7);
  
  public int sumSubarrayMins(int[] A) {
    int[] r = new int[A.length];
    int[] l = new int[A.length];
    Stack<Integer> stack = new Stack<>();
    
    for (int i = A.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
        stack.pop();
      }
      r[i] = stack.isEmpty() ? A.length : stack.peek();
      stack.push(i);
    }
    stack.clear();
    for (int i = 0; i < A.length; i++) {
      //Must be A[i] < A[stack.peek()]rather than A[i] <= A[stack.peek()]
      //In order to handle case like [1,3,1], where 1 might count repeatedly.
      while (!stack.isEmpty() && A[i] < A[stack.peek()]) {
        stack.pop();
      }
      l[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }
    
    long res = 0;
    for (int i = 0; i < A.length; i++) {
      res += A[i] * (r[i] - i) * (i - l[i]);
      res %= MOD;
    }
    
    return (int) res;
  }
  
  @Test
  public void testSumSubarrayMins() {
    int[][] As = {{3,1,2,4}};
    int[] res = {17};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], sumSubarrayMins(As[i]));
    }
  }

}
