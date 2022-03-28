package algorithm.leetcode.p051to100;

import java.util.Stack;

public class LeetCode085MaximalRectangle {
  
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 
        || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    int[] prev = new int[matrix[0].length];
    int[] curr = new int[matrix[0].length];
    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
      int[] temp = curr;
      curr = prev;
      prev = temp;
      for (int j = 0; j < matrix[i].length; j++) {
        curr[j] = (matrix[i][j] == '1' ? prev[j] + 1: 0);
      }
      Stack<Integer> stack = new Stack<>();
      for (int j = 0; j <= matrix[i].length; j++) {
        int height = (j != matrix[i].length ? curr[j] : 0);
        if (stack.isEmpty() || curr[stack.peek()] <= height) {
          stack.push(j);
        } else {
          int area = curr[stack.pop()] * (stack.isEmpty() ? j : j - 1 - stack.peek());
          max = Math.max(max, area);
          j--;
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    char[][] matrix = {{'1','0','1','0','0'},
                      {'1','0','1','1','1'},
                      {'1','1','1','1','1'},
                      {'1','0','0','1','0'}};
    LeetCode085MaximalRectangle one = new LeetCode085MaximalRectangle();
    System.out.println(one.maximalRectangle(matrix));
  }

}
