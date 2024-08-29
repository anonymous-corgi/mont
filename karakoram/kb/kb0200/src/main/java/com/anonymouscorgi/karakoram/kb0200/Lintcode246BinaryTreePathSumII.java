package com.anonymouscorgi.karakoram.kb0200;

import com.anonymouscorgi.karakoram.base.TreeNode;
import java.util.List;

/**
 * Lintcode 246. Binary Tree Path Sum II
 * <p>
 * Your are given a binary tree in which each node contains a value. Design an algorithm to get all
 * paths which sum to a given value. The path does not need to start or end at the root or a leaf,
 * but it must go in a straight line down.
 * <p>
 * Example 1:
 * <p>
 * Input: {1,2,3,4,#,2} 6
 * <p>
 * Output: [ [2, 4], [1, 3, 2] ] Explanation: The binary tree is like this:
 *     1
 *    / \
 *   2   3
 *  /   /
 * 4   2
 * for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
 * <p>
 * Example 2:
 * <p>
 * Input: {1,2,3,4} 10
 * <p>
 * Output: [] Explanation: The binary tree is like this:
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * for target 10, there is no
 * way to reach it.
 */
interface Lintcode246BinaryTreePathSumII {
  List<List<Integer>> binaryTreePathSum2(TreeNode root, int target);

  Lintcode246BinaryTreePathSumII Traversal = new Lintcode246BinaryTreePathSumII() {
    @Override
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
      return null;
    }
  };
}
