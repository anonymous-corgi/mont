package leetcode.p101to150;

import basicclass.TreeNode;

public class LeetCode114FlattenBinaryTreeToLinkedList {

  private TreeNode prev = null;

  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
  }

}
