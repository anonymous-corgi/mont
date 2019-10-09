package leetcode.p501to550;

import basicclass.TreeNode;

@SuppressWarnings("unused")
public class LeetCode543DiameterOfBinaryTree {

    private interface Method {
        int diameterOfBinaryTree(TreeNode root);
    }

    private static class BackTracking implements Method {

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
