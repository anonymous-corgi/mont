package interview.fb.phoneinterview;

import java.util.Stack;

public class LeetCode032LongestValidParentheses {
  
  public int longestValidParentheses(String s) {
    if (s == null) {
      return 0;
    }
    int sLen = s.length();
    if (sLen == 0) {
      return 0;
    }
    int left = -1;
    int max = 0;
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 0; i < sLen; i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else {
        if (stack.isEmpty()) {
          left = i;
        } else {
          stack.pop();
          if (stack.isEmpty()) {
            max = Math.max(max, i - left);
          } else {
            max = Math.max(max, i - stack.peek());
          }          
        }
      }
    }
    return max;
  }
  

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String str = "()";
    System.out.println(new LeetCode032LongestValidParentheses().longestValidParentheses(str));

  }

}
