package algorithm.leetcode.p051to100;

import java.util.Stack;

import algorithm.base.TreeNode;

@SuppressWarnings("unused")
public class LeetCode099RecoverBinarySearchTree {

    private interface Method {
        void recoverTree(TreeNode root);
    }

    private static class DFS implements Method {
        private TreeNode last;
        private TreeNode candidate1;
        private TreeNode candidate2;

        @Override
        public void recoverTree(TreeNode root) {
            dfs(root);
            int temp = candidate1.val;
            candidate1.val = candidate2.val;
            candidate2.val = temp;
        }

        private void dfs(TreeNode root) {
            if (root == null) {
                return;
            }

            dfs(root.left);
            if (last == null || last.val < root.val) {
                last = root;
            } else {
                candidate1 = last;
                candidate2 = root;
            }
            dfs(root.right);
        }
    }

    private static class NonRecursive implements Method {

        @Override
        public void recoverTree(TreeNode root) {
            TreeNode last = null;
            TreeNode candidate1 = null;
            TreeNode candidate2 = null;
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (last == null || last.val < root.val) {
                        last = root;
                    } else {
                        candidate1 = last;
                        candidate2 = root;
                    }
                    root = root.right;
                }
            }

            int temp = candidate1.val;
            candidate1.val = candidate2.val;
            candidate2.val = temp;
        }
    }
}
