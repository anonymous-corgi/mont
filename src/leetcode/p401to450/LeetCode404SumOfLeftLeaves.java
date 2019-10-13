package leetcode.p401to450;

import algorithm.base.TreeNode;

public class LeetCode404SumOfLeftLeaves {
  
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return helper(root, false);
  }
  
  private int helper(TreeNode root, Boolean isLeft) {
    if (root.left == null && root.right == null
        && isLeft) {
      return root.val;
    }
    int sum = 0;
    if (root.left != null) {
      sum += helper(root.left, true);
    }
    if (root.right != null) {
      sum += helper(root.right, false);
    }
    return sum;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
