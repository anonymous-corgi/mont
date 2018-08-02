package leetcode.p151to200;

import java.util.Stack;

public class LeetCode155MinStack {
  
  private final Stack<Integer> minStack = new Stack<>();
  private final Stack<Integer> norStack = new Stack<>();

  public void push(int number) {
      if (norStack.isEmpty()) {
          minStack.push(number);
      } else {
          minStack.push(Math.min(number, minStack.peek()));
      }
      norStack.push(number);
  }

  public int pop() {
      minStack.pop();
      return norStack.pop();
  }
  
  public int min() {
      return minStack.peek();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
