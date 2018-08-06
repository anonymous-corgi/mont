package leetcode.p151to200;

import java.util.Stack;

public class LeetCode155MinStack {
  
//  One stack method:
  private long min;
  private final Stack<Long> stack;
  public LeetCode155MinStack() {
    stack = new Stack<>();
  }
  
  public void push(int x) {
    long num = (long) x;
    if (stack.isEmpty()) {
      //!!!!!!push(0l)
      stack.push(0L);
      min = num;
    } else {
      stack.push(num - min);
      if (num < min) {
        min = num;
      }
    }
  }
  
  public void pop() {
    if (stack.isEmpty()) {
      return;
    }
    long num = stack.pop();
    if (num < 0) {
      min -= num;
    }
  }
  
  public int top() {
    long num = stack.peek();
    if (num < 0) {
      return (int) min;
    } else {
      return (int) (num + min);
    }
  }
  
  public int getMin() {
    return (int) min;
  }
  
//  Two stacks method:
//  private final Stack<Integer> minStack = new Stack<>();
//  private final Stack<Integer> norStack = new Stack<>();
//
//  public void push(int number) {
//      if (norStack.isEmpty()) {
//          minStack.push(number);
//      } else {
//          minStack.push(Math.min(number, minStack.peek()));
//      }
//      norStack.push(number);
//  }
//
//  public int pop() {
//      minStack.pop();
//      return norStack.pop();
//  }
//  
//  public int min() {
//      return minStack.peek();
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
