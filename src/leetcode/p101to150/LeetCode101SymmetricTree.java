package leetcode.p101to150;

import basicclass.TreeNode;

public class LeetCode101SymmetricTree {

    private interface Method {
        boolean isSymmetric(TreeNode root);
    }

    private static final class DFS implements Method {

        @Override
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode l, TreeNode r) {
            if (l == null && r == null) {
                return true;
            }
            if (l == null || r == null || l.val != r.val) {
                return false;
            }
            return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
        }
    }
}
