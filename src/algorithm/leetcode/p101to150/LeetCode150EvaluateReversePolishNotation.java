package algorithm.leetcode.p101to150;

import java.util.Stack;

public class LeetCode150EvaluateReversePolishNotation {
  
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      if ("+".equals(tokens[i])) {
        int b = stack.pop();
        int f = stack.pop();
        int res = f + b;
        stack.push(res);
      } else if ("-".equals(tokens[i])) {
        int b = stack.pop();
        int f = stack.pop();
        int res = f - b;
        stack.push(res);
      } else if ("*".equals(tokens[i])) {
        int b = stack.pop();
        int f = stack.pop();
        int res = f * b;
        stack.push(res);
      } else if ("/".equals(tokens[i])) {
        int b = stack.pop();
        int f = stack.pop();
        int res = f / b;
        stack.push(res);
      } else {
        int res = Integer.parseInt(tokens[i]);
        stack.push(res);
      }
    }
    return stack.pop();
  }

  public static void main(String[] args) {

  }

}
