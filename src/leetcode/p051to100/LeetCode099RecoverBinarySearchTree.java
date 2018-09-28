package leetcode.p051to100;

import basicclass.TreeNode;

public class LeetCode099RecoverBinarySearchTree {
  
  private TreeNode prev;
  private TreeNode candidate1;
  private TreeNode candidate2;
  
  public void recoverTree(TreeNode root) {
    dfs(root);
    int temp = candidate1.val;
    candidate1.val = candidate2.val;
    candidate2.val = temp;
  }
  
  private void dfs(TreeNode root) {
    if (root == null) {
      return;
    }
    dfs(root.left);
    
    if (prev == null || prev.val < root.val) {
      prev = root;
    } else {
      candidate1 = prev;
      candidate2 = root;
    }
    
    dfs(root.right);
  }

}
