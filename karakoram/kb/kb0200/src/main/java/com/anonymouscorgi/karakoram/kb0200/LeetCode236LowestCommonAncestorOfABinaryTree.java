package com.anonymouscorgi.karakoram.kb0200;

import com.anonymouscorgi.karakoram.base.TreeNode;

interface LeetCode236LowestCommonAncestorOfABinaryTree {

  TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);

  LeetCode236LowestCommonAncestorOfABinaryTree Method = new LeetCode236LowestCommonAncestorOfABinaryTree() {
    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      return lowestCommonAncestor(root, p, q, new int[]{0});
    }

    private static TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q,
        int[] hasNode) {
      if (node == null) {
        return null;
      }

      boolean hasNoMatchedNode = hasNode[0] == 0;
      if (node.val == p.val || node.val == q.val) {
        hasNode[0]++;
      }
      if (hasNode[0] == 2) {
        return null;
      }

      TreeNode result = lowestCommonAncestor(node.left, p, q, hasNode);
      if (result != null) {
        return result;
      } else if (hasNode[0] == 2) {
        return hasNoMatchedNode ? node : null;
      }

      result = lowestCommonAncestor(node.right, p, q, hasNode);
      if (result != null) {
        return result;
      } else if (hasNode[0] == 2) {
        return hasNoMatchedNode ? node : null;
      }
      return null;
    }
  };
}
