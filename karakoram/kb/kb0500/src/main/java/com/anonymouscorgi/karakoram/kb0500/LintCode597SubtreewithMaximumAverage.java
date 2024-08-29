package com.anonymouscorgi.karakoram.kb0500;

import com.anonymouscorgi.karakoram.base.TreeNode;

/**
 * LintCode 597. Subtree with Maximum Average
 * <p>
 * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
 * <p>
 * Notice LintCode will print the subtree which root is your return node. It's guaranteed that there
 * is only one subtree with maximum average.
 * <p>
 * Example
 * <p>
 * Given a binary tree:
 * <p>
 *      1
 *    /   \
 *  -5     11
 *  / \   /  \
 * 1   2 4    -2
 * return the node11.
 */
final class LintCode597SubtreewithMaximumAverage {

  interface Algorithm {

    TreeNode findSubtree(TreeNode root);
  }

  static Algorithm DivideConquer = new Algorithm() {

    class Result {

      int sum;
      int nodeCount;
      double maxAve;
      TreeNode maxTreeNode;

      public Result(int sum, int nodeCount, double maxAve, TreeNode maxTreeNode) {
        this.sum = sum;
        this.nodeCount = nodeCount;
        this.maxAve = maxAve;
        this.maxTreeNode = maxTreeNode;
      }
    }

    @Override
    public TreeNode findSubtree(TreeNode root) {
      if (root == null) {
        return null;
      }
      return findMax(root).maxTreeNode;
    }


    private Result findMax(TreeNode t) {
      if (t == null) {
        return new Result(0, 0, Double.NEGATIVE_INFINITY, null);
      }

      Result lt = findMax(t.left);
      Result rt = findMax(t.right);

      int number = lt.nodeCount + rt.nodeCount + 1;
      int sum = lt.sum + rt.sum + t.val;
      double average = (double) sum / number;

      if (average > Math.max(lt.maxAve, rt.maxAve)) {
        return new Result(sum, number, average, t);
      } else if (lt.maxAve > rt.maxAve) {
        return new Result(sum, number, lt.maxAve, lt.maxTreeNode);
      } else {
        return new Result(sum, number, rt.maxAve, rt.maxTreeNode);
      }
    }
  };
}
