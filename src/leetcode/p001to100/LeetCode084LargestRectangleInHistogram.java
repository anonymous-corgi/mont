package leetcode.p001to100;

import java.util.Stack;

public class LeetCode084LargestRectangleInHistogram {

  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int max = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i <= heights.length; i++) {
      int h = i == heights.length ? 0 : heights[i];
      if (stack.isEmpty() || h >= heights[stack.peek()]) {
        stack.push(i);
      } else {
        int ht = stack.pop();
        max = Math.max(max, heights[ht] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
        i--;
      }
    }
    return max;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
