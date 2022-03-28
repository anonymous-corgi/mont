package algorithm.leetcode.p101to150;

import algorithm.base.TreeNode;

public class LeetCode114FlattenBinaryTreeToLinkedList {

    private interface Method {
        void flatten(TreeNode root);
    }

    private static final class LastPointer implements Method {

        @Override
        public void flatten(TreeNode root) {
            flatten(root, new TreeNode[1]);
        }

        private void flatten(TreeNode root, TreeNode[] last) {
            if (root == null) {
                return;
            }
            flatten(root.right, last);
            flatten(root.left, last);
            root.right = last[0];
            root.left = null;
            last[0] = root;
        }
    }
}
