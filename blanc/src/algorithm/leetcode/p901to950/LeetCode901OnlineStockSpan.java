package algorithm.leetcode.p901to950;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode901OnlineStockSpan {

  class StockSpanner {
    
    private int index = 0;
    private List<Integer> list;
    private Stack<Integer> stack;
    
    public StockSpanner() {
      stack = new Stack<Integer>();
      list = new ArrayList<Integer>();
    }
    
    public int next(int price) {
      while (!stack.isEmpty() && list.get(stack.peek()) <= price) {
        stack.pop();
      }
      int last = stack.isEmpty() ? -1 : stack.peek();
      stack.push(index);
      list.add(price);
      return index++ - last;
    }
    
  }
  
}
