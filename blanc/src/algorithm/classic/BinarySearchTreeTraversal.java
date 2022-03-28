package algorithm.classic;

import algorithm.base.TreeNode;

import java.util.*;

@SuppressWarnings("unused")
public class BinarySearchTreeTraversal {

    private interface Traversal {
        List<Integer> traversal(TreeNode root);
    }

    // In-Order InOrder Recursive //////////////////////////////////////////////////////////////////////////////////////
    private static class InOrderRecursive implements Traversal {

        @Override
        public List<Integer> traversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>();
            traversal(root, result);
            return result;
        }

        private void traversal(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            traversal(root.left, result);
            result.add(root.val);
            traversal(root.right, result);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Non-Recursive ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // In-Order InOrder Non-Recursive //////////////////////////////////////////////////////////////////////////////////
    private static class InOrder implements Traversal {

        @Override
        public List<Integer> traversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    result.add(root.val);
                    root = root.right;
                }
            }
            return result;
        }
    }

    // Pre-Order PreOrder Non-Recursive ////////////////////////////////////////////////////////////////////////////////
    private static class PreOrder implements Traversal {

        @Override
        public List<Integer> traversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    result.add(root.val);
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    root = root.right;
                }
            }
            return result;
        }
    }

    // Post-Order PostOrder Non-Recursive //////////////////////////////////////////////////////////////////////////////
    private static class PostOrder implements Traversal {

        @Override
        public List<Integer> traversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            TreeNode prev = null;
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.peek();
                    if (root.right != null && root.right != prev) {
                        root = root.right;
                    } else {
                        result.add(root.val);
                        prev = stack.pop();
                        root = null;
                    }
                }
            }
            return result;
        }
    }

    private static class PostOrder2 implements Traversal {
        @Override
        public List<Integer> traversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            LinkedList<Integer> result = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    result.addFirst(root.val);
                    stack.push(root);
                    //For PreOreder Traversal is: root = stack.pop());
                    root = root.right;
                } else {
                    root = stack.pop();
                    root = root.left;
                }
            }
            return result;
        }
    }
}
