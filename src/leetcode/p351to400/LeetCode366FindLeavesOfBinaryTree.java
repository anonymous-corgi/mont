package leetcode.p351to400;

import java.util.ArrayList;
import java.util.List;

import algorithm.base.TreeNode;

/**
 * 366. Find Leaves of Binary Tree
 * Medium
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 *
 * Example:
 * Input: [1,2,3,4,5]
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 */
public class LeetCode366FindLeavesOfBinaryTree {

    private interface Method {
        List<List<Integer>> findLeaves(TreeNode root);
    }

    private static final class ListList implements Method {

        @Override
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res);
            return res;
        }

        private int dfs(TreeNode node, List<List<Integer>> res) {
            if (node == null) {
                return -1;
            }
            int lHeight = dfs(node.left, res);
            int rHeight = dfs(node.right, res);
            int height = Math.max(lHeight, rHeight) + 1;
            if (height >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(height).add(node.val);
            return height;
        }
    }
}
