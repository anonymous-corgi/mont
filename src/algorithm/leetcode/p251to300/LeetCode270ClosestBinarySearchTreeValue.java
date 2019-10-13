package algorithm.leetcode.p251to300;

import algorithm.base.TreeNode;

/**
 * LeetCode 270. Closest Binary Search Tree Value
 * Easy
 *
 * Given a non-empty binary search tree and a target value,
 * find the value in the BST that is closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 *
 * Example:
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 */
public class LeetCode270ClosestBinarySearchTreeValue {

    private interface Method {
        int closestValue(TreeNode root, double target);
    }

    private static final class BackTracking implements Method {

        @Override
        public int closestValue(TreeNode root, double target) {
            int res = root.val;
            if (root.val < target && root.right != null) {
                int childVal = closestValue(root.right, target);
                if (Math.abs(childVal - target) < Math.abs(res - target)) {
                    res = childVal;
                }
            } else if (root.val > target && root.left != null) {
                int childVal = closestValue(root.left, target);
                if (Math.abs(childVal - target) < Math.abs(res - target)) {
                    res = childVal;
                }
            }
            return res;
        }
    }
}
