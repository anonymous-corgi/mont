package algorithm.leetcode.p651to700;

import algorithm.base.TreeNode;

public class LeetCode687LongestUnivaluePath {
  
  private int aMax = 0;
  
  public int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return 0;
    }
    helper(root);
    return aMax;
  }
  
  private int helper(TreeNode root) {
    int tMax = 0;
    int lMax = 0;
    int rMax = 0;
    if (root.left != null) {
      lMax = helper(root.left);
      if (root.left.val == root.val) {
        tMax += ++lMax;
      } else {
          lMax = 0;
      }
    }
    if (root.right != null) {
      rMax = helper(root.right);
      if (root.right.val == root.val) {
        tMax += ++rMax;
      } else {
          rMax = 0;
      }
    }
    aMax = Math.max(aMax, tMax);
    return Math.max(lMax, rMax);
  }

  public static void main(String[] args) {
    
  }

}
