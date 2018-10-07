package leetcode.p851to900;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode862ShortestSubarrayWithSumAtLeastK {
  
  interface Method {
    int shortestSubarray(int[] nums, int K);
  }
  
  public static class Standard implements Method {

    @Override
    public int shortestSubarray(int[] nums, int K) {
      int LEN = nums.length + 1;
      int res = LEN;
      int[] prefix = new int[LEN];
      for (int i = 0; i < nums.length; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
      }
      Deque<Integer> que = new ArrayDeque<>();
      for (int i = 0; i < LEN; i++) {
        while (!que.isEmpty() && prefix[i] - prefix[que.peekFirst()] >=  K) {
          res = Math.min(res, i - que.pollFirst());
        }
        while (!que.isEmpty() && prefix[que.peekLast()] >= prefix[i]) {
          que.pollLast();
        }
        que.addLast(i);
      }
      return res < LEN ? res : -1;
    }
    
  }
  
  private Method getInstance() {
    return new Standard();
  }
  
  public int shortestSubarray(int[] nums, int K) {
    return getInstance().shortestSubarray(nums, K);
  }

  public static void main(String[] args) {
    int[] nums = {84,-37,32,40,95};
    int K = 167;
    LeetCode862ShortestSubarrayWithSumAtLeastK one =
        new LeetCode862ShortestSubarrayWithSumAtLeastK();
    System.out.println(one.shortestSubarray(nums, K));
  }

}
