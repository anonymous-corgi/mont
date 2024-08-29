package com.anonymouscorgi.karakoram.jiuzhang.c3.divideconquer;

import com.anonymouscorgi.karakoram.base.TreeNode;

/**
 * LintCode 596. Minimum Subtree
 * <p>
 * Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
 * <p>
 * Notice LintCode will print the subtree which root is your return node. It's guaranteed that there
 * is only one subtree with minimum sum and the given binary tree is not an empty tree.
 */
interface LintCode596MinimumSubtree {

  TreeNode findSubtree(TreeNode root);

  LintCode596MinimumSubtree DivideConquer = new LintCode596MinimumSubtree() {

    class Result {

      final int sum;
      final int minSum;
      final TreeNode minTreeNode;

      public Result(int sum, int minSum, TreeNode minTreeNode) {
        this.sum = sum;
        this.minSum = minSum;
        this.minTreeNode = minTreeNode;
      }
    }

    @Override
    public TreeNode findSubtree(TreeNode root) {
      if (root == null) {
        return null;
      }
      return findMin(root).minTreeNode;
    }

    private Result findMin(TreeNode t) {
      if (t == null) {
        return new Result(0, Integer.MAX_VALUE, t);
      }

      Result leftMin = findMin(t.left);
      Result rightMin = findMin(t.right);

      int sum = t.val + leftMin.sum + rightMin.sum;
      if (sum < Math.min(leftMin.minSum, rightMin.minSum)) {
        return new Result(sum, sum, t);
      } else if (leftMin.minSum < rightMin.minSum) {
        return new Result(sum, leftMin.minSum, leftMin.minTreeNode);
      } else {
        return new Result(sum, rightMin.minSum, rightMin.minTreeNode);
      }
    }
  };
}
