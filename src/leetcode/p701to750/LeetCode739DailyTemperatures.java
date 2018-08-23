package leetcode.p701to750;

import java.util.Stack;

public class LeetCode739DailyTemperatures {
  
  public int[] dailyTemperatures(int[] temperatures) {
    int[] res = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < temperatures.length; i++) {
      if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) {
        stack.push(i);
      } else {
        int index = stack.pop();
        res[index] = i - index;
        i--;
      }
    }
    return res;
  }

  public static void main(String[] args) {

  }

}
