package algorithm.leetcode.p201to250;

import algorithm.base.TreeNode;

import java.util.Stack;

public class LeetCode230KthSmallestElementInBST {

    private interface Method {
        int kthSmallest(TreeNode root, int k);
    }

    private static final class NorRecursive implements Method {

        public int kthSmallest(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            int cursor = 0;
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (++cursor == k) {
                        return root.val;
                    }
                }
            }
            return -1;
        }
    }

    private static final class NorRecursiveIterator implements Method {

        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            for (int i = 0; i < k - 1; i++) {
                TreeNode cursor = stack.pop();
                TreeNode right = cursor.right;
                while (right != null) {
                    stack.push(right);
                    right = right.left;
                }
            }

            return stack.peek().val;
        }
    }
}
