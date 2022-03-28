package algorithm.leetcode.p501to550;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode503NextGreaterElementII {
  
  public int[] nextGreaterElements(int[] nums) {
    int nLen = nums.length;
    int[] res = new int[nLen];
    Arrays.fill(res, -1);
    Stack<Integer> stack = new Stack<>();
    for (int i = 0, iLen = nLen * 2; i < iLen; i++) {
      int index = i % nLen;
      if (stack.isEmpty() || nums[stack.peek()] >= nums[index]) {
        stack.push(index);
      } else {
        res[stack.pop()] = nums[index];
        i--;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {1,2,1};
    LeetCode503NextGreaterElementII one =
        new LeetCode503NextGreaterElementII();
    one.nextGreaterElements(nums);

  }

}
