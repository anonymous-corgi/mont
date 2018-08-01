package leetcode.p501to550;

import basicclass.TreeNode;

public class LeetCode543DiameterOfBinaryTree {
  
  private int maxLen = 0;
  
  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    helper(root);
    return maxLen;
  }
  
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left);
    int right = helper(root.right);
    maxLen = Math.max(maxLen, left + right);
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
