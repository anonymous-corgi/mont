package leetcode.p301to350;

import algorithm.base.TreeNode;

/**
 * LeetCode 337. House Robber III
 * Medium
 *
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides the root,
 * each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 */
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
}
