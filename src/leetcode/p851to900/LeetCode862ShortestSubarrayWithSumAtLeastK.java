package leetcode.p851to900;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode862ShortestSubarrayWithSumAtLeastK {
  //
  public int shortestSubarray(int[] nums, int K) {
    int LEN = nums.length;
    int res = LEN + 1;
    int[] prefix = new int[LEN + 1];
    for (int i = 0; i < LEN; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }
    Deque<Integer> que = new ArrayDeque<>();
    for (int i = 0; i <= LEN; i++) {
      while (que.size() > 0 && prefix[i] - prefix[que.peekFirst()] >=  K) {
        res = Math.min(res, i - que.pollFirst());
      }
      while (que.size() > 0 && prefix[i] <= prefix[que.peekLast()]) {
        que.pollLast();
      }
      que.addLast(i);
    }
    return res <= LEN ? res : -1;
  }

  public static void main(String[] args) {
    int[] nums = {84,-37,32,40,95};
    int K = 167;
    LeetCode862ShortestSubarrayWithSumAtLeastK one =
        new LeetCode862ShortestSubarrayWithSumAtLeastK();
    System.out.println(one.shortestSubarray(nums, K));
  }

}
