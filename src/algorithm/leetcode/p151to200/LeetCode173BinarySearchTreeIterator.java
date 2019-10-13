package algorithm.leetcode.p151to200;

import java.util.Stack;

import algorithm.base.TreeNode;

public class LeetCode173BinarySearchTreeIterator {

    private static abstract class BSTIterator {

        /**
         * @return the next smallest number
         */
        abstract int next();

        /**
         * @return whether we have a next smallest number
         */
        abstract boolean hasNext();
    }

    private static class Stack_Method extends BSTIterator {

        private Stack<TreeNode> nodes;

        private Stack_Method(TreeNode root) {
            nodes = new Stack<>();
            while (root != null) {
                nodes.push(root);
                root = root.left;
            }
        }

        @Override
        int next() {
            TreeNode cursor = nodes.pop();
            TreeNode node = cursor.right;
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
            return cursor.val;
        }

        @Override
        boolean hasNext() {
            return !nodes.isEmpty();
        }
    }
}
