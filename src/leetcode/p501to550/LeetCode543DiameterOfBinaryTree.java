package leetcode.p501to550;

import basicclass.TreeNode;

@SuppressWarnings("unused")
public class LeetCode543DiameterOfBinaryTree {

    private interface Method {
        int diameterOfBinaryTree(TreeNode root);
    }

    private static class Traversal implements Method {

        private int maxLen = 0;

        @Override
        public int diameterOfBinaryTree(TreeNode root) {
            maxLen = 0;
            helper(root);
            return maxLen;
        }

        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = helper(root.left);
            int right = helper(root.right);
            maxLen = Math.max(maxLen, left + right);
            return Math.max(left, right) + 1;
        }
    }

    private static class BackTracking implements Method {

        @Override
        public int diameterOfBinaryTree(TreeNode root) {
            return helper(root)[1];
        }

        private int[] helper(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] lRes = helper(root.left);
            int[] rRes = helper(root.right);
            int sideMax = Math.max(lRes[0], rRes[0]) + 1;
            int max = Math.max(lRes[0] + rRes[0], Math.max(lRes[1], rRes[1]));
            return new int[]{sideMax, max};
        }
    }
}
