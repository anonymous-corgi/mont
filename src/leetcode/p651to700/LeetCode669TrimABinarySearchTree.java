package leetcode.p651to700;

import basicclass.TreeNode;

public class LeetCode669TrimABinarySearchTree {

    private interface Method {
        TreeNode trimBST(TreeNode root, int L, int R);
    }

    private static final class Normal implements Method {

        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
                return null;
            }
            if (root.val < L) {
                return trimBST(root.left, L, R);
            } else if (root.val > R) {
                return trimBST(root.right, L, R);
            } else {
                root.left = trimBST(root.left, L, R);
                root.right = trimBST(root.right, L, R);
                return root;
            }
        }
    }
}
