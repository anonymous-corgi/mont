package leetcode.p201to250;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode239SlidingWindowMaximum {
  
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || nums.length == 0) {
      return new int[0];
    }
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
      while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
        deque.poll();
      }
      
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.pollLast();
      }
      
      deque.offer(i);
      if (i >= k - 1) {
        res[i - k + 1] = nums[deque.peekFirst()];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
