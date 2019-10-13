package algorithm.leetcode.p051to100;

import java.util.Stack;
import algorithm.base.TreeNode;

@SuppressWarnings("unused")
public class LeetCode098ValidateBinarySearchTree {

    private interface Method {
        boolean isValidBST(TreeNode root);
    }

    private static final class NonRecursive implements Method {

        @Override
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
            pushAll(stack, root);
            TreeNode first = stack.pop();
            int last = first.val;
            pushAll(stack, first.right);
            while (!stack.isEmpty()) {
                TreeNode cursor = stack.pop();
                if (cursor.val > last) {
                    last = cursor.val;
                    pushAll(stack, cursor.right);
                } else {
                    return false;
                }
            }
            return true;
        }

        private void pushAll(Stack<TreeNode> stack, TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }

    private static final class NonRecursive2 implements Method {

        @Override
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            int last = 0;
            boolean isFirst = true;
            TreeNode cursor = root;
            Stack<TreeNode> stack = new Stack<>();
            while (cursor != null || !stack.isEmpty()) {
                if (cursor != null) {
                    stack.push(cursor);
                    cursor = cursor.left;
                } else {
                    TreeNode node = stack.pop();
                    cursor = node.right;
                    if (isFirst || last < node.val) {
                        isFirst = false;
                        last = node.val;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
