package leetcode.p101to150;

import basicclass.TreeNode;

public class LeetCode124BinaryTreeMaximumPathSum {
  
  private int max = Integer.MIN_VALUE;
  
  public int maxPathSum(TreeNode root) {
    helper(root);
    return max;
  }
  
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int lMax = helper(root.left);
    int rMax = helper(root.right);
    max = Math.max(max, lMax + rMax + root.val);
    
    return Math.max(0, (lMax > rMax ? lMax : rMax) + root.val);
  }

  public static void main(String[] args) {

  }

}
