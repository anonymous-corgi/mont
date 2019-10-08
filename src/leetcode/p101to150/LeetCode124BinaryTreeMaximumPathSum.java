package leetcode.p101to150;

import basicclass.TreeNode;

public class LeetCode124BinaryTreeMaximumPathSum {

    private interface Method {
        int maxPathSum(TreeNode root);
    }

    private static final class Traversal implements Method {

        public int maxPathSum(TreeNode root) {
            int[] res = new int[]{root.val};
            maxPathSum(root, res);
            return res[0];
        }

        private int maxPathSum(TreeNode root, int[] res) {
            if (root == null) {
                return 0;
            }
            int lMax = maxPathSum(root.left, res);
            int rMax = maxPathSum(root.right, res);
            res[0] = Math.max(res[0], root.val + lMax + rMax);
            return Math.max(0, root.val + Math.max(lMax, rMax));
        }
    }
}