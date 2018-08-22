package leetcode.p301to350;

import basicclass.TreeNode;

public class LeetCode337HouseRobberIII {
  
  public int rob(TreeNode root) {
    int[] r = dfs(root);
    return Math.max(r[0], r[1]);
  }
  
  private int[] dfs(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }
    int[] lRes = dfs(root.left);
    int[] rRes = dfs(root.right);
    int r0 = Math.max(lRes[0], lRes[1]) + Math.max(rRes[0], rRes[1]);
    int r1 = root.val + lRes[0] + rRes[0];
    return new int[]{r0, r1};
  }

  public static void main(String[] args) {

  }

}
