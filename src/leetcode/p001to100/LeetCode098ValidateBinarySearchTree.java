package leetcode.p001to100;

import java.util.Stack;
import basicclass.TreeNode;

public class LeetCode098ValidateBinarySearchTree {
  
  //Use non-recursive way to solve the problem.
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    Stack<TreeNode> stack = new Stack<>();
    pushAll(stack, root);
    TreeNode first = stack.pop();
    int last = first.val;
    pushAll(stack, first.right);
    while (!stack.isEmpty()) {
      TreeNode cursor = stack.pop();
      if (cursor.val > last) {
        last = cursor.val;
        pushAll(stack, cursor.right);
      } else {
        return false;
      }
    }
    return true;
  }
  
  private void pushAll(Stack<TreeNode> stack, TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
