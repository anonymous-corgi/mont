package leetcode.p201to250;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode239SlidingWindowMaximum {
  
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || nums.length == 0) {
      return new int[0];
    }
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> maxQue = new ArrayDeque<>(k);
    for (int i = 0; i < nums.length; i++) {
      while (!maxQue.isEmpty() && maxQue.peekFirst() <= i - k) {
        maxQue.pollFirst();
      }
      
      while (!maxQue.isEmpty() && nums[maxQue.peekLast()] < nums[i]) {
        maxQue.pollLast();
      }
      
      maxQue.offer(i);
      if (i >= k - 1) {
        res[i - k + 1] = nums[maxQue.peekFirst()];
      }
    }
    return res;
  }

}
