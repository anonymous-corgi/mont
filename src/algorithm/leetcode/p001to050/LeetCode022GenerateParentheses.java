package algorithm.leetcode.p001to050;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import algorithm.base.utils.Print;

public class LeetCode022GenerateParentheses {
  //Bloomberg
  //Use Stack to accomplish
  class Node {
    String str;
    int open;
    int close;
    boolean[] neis;
    public Node(String s, int o, int c) {
      this.str = s;
      this.open = o;
      this.close = c;
      this.neis = new boolean[2];
    }
  }
  
  public List<String> generateParenthesis(int n) {
    Stack<Node> stack = new Stack<>();
    List<String> list = new ArrayList<>();
    stack.push(new Node("(", 1, 0));
    
    while (!stack.isEmpty()) {
      Node cursor = stack.peek();
      
      if (!cursor.neis[0] && cursor.open < n) {
        cursor.neis[0] = true;
        stack.push(new Node(cursor.str + '(', cursor.open + 1, cursor.close));
        continue;
      }
      if (!cursor.neis[1] && cursor.close < cursor.open) {
        cursor.neis[1] = true;
        stack.push(new Node(cursor.str + ')', cursor.open, cursor.close + 1));
        continue;
      }
      if (cursor.open == n && cursor.close == n) {
        list.add(cursor.str);
      }
      stack.pop();
    }
    return list;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode022GenerateParentheses one = 
        new LeetCode022GenerateParentheses();
    Print.printList(one.generateParenthesis(3));
  }

}
