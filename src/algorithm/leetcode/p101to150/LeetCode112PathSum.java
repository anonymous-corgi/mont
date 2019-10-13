package algorithm.leetcode.p101to150;

import algorithm.base.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode112PathSum {
  //Non-recursive Method
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    int count = 0;
    TreeNode last = null;
    Deque<TreeNode> stack = new ArrayDeque<>();
    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        count += root.val;
        stack.push(root);
        root = root.left;
      } else {
        TreeNode prev = stack.pop();
        if (prev.right == null) {
          if (prev.left == null && count == sum) {
            return true;
          }
          count -= prev.val;
          last = prev;
        } else if (prev.right == last) {
          count -= prev.val;
          last = prev;
        } else {
          stack.push(prev);
          root = prev.right;
        }
      }
    }
    return false;
  }

}
