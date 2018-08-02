package leetcode.p201to250;

import java.util.Stack;
import basicclass.TreeNode;

public class LeetCode230KthSmallestElementInBST {
  
  public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
    
    for (int i = 0; i < k - 1; i++) {
      TreeNode cursor = stack.pop();
      TreeNode right = cursor.right;
      while (right != null) {
        stack.push(right);
        right = right.left;
      }
    }
    
    return stack.peek().val;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
