package leetcode.p901to950;

import static org.junit.Assert.*;
import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.Test;

public class LeetCode918MaximumSumCircularSubarray {
  
  interface Method {
    int maxSubarraySumCircular(int[] A);
  }
  
  public static class Deque_method implements Method {

    @Override
    public int maxSubarraySumCircular(int[] A) {
      int max = Integer.MIN_VALUE;
      int[] prefix = new int[A.length * 2 + 1];
      for (int i = 0, iLen = prefix.length - 1; i < iLen; i++) {
        prefix[i + 1] = prefix[i] + A[i % A.length];
      }
      Deque<Integer> que = new ArrayDeque<>(A.length + 1);
      que.offer(0);
      for (int i = 1; i < prefix.length; i++) {
        while (!que.isEmpty() && que.peekFirst() < i - A.length) {
          que.pollFirst();
        }
        max = Math.max(max, prefix[i] - prefix[que.peekFirst()]);
        while (!que.isEmpty() && prefix[que.peekLast()] >= prefix[i]) {
          que.pollLast();
        }
        que.offer(i);
      }
      return max;
    }
    
  }
  
  public static class Solution_method implements Method {

    @Override
    public int maxSubarraySumCircular(int[] A) {
      int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
      for (int a : A) {
          curMax = Math.max(curMax + a, a);
          maxSum = Math.max(maxSum, curMax);
          curMin = Math.min(curMin + a, a);
          minSum = Math.min(minSum, curMin);
          total += a;
      }
      return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
    
  }
  
  private Method getMethod() {
    return new Deque_method();
  }
  
  public int maxSubarraySumCircular(int[] A) {
    return getMethod().maxSubarraySumCircular(A);
  }

  @Test
  public void testMaxSubarraySumCircular() {
    int[][] A = {{1,-2,3,-2}, {5, -3, 5}, {3, -1, 2, -1}, {3, -2, 2, -3}, {-2, -3, -1}};
    int[] res = {3, 10, 4, 3, -1};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], maxSubarraySumCircular(A[i]));      
    }
  }
  
  public static void main(String[] args) {
    LeetCode918MaximumSumCircularSubarray one =
        new LeetCode918MaximumSumCircularSubarray();
    int[] A = {1,-2,3,-2};
    System.out.println(one.maxSubarraySumCircular(A));
  }

}
