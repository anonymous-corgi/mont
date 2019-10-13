package leetcode.p251to300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithm.base.TreeNode;

@SuppressWarnings("unused")
public class LeetCode257BinaryTreePaths {

    private interface Method {
        List<String> binaryTreePaths(TreeNode root);
    }

    private static class BackTracking implements Method {

        @Override
        public List<String> binaryTreePaths(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }

            List<String> paths = new ArrayList<>();
            if (root.left == null && root.right == null) {
                paths.add(root.val + "");
                return paths;
            }

            for (String path : binaryTreePaths(root.left)) {
                paths.add(root.val + "->" + path);
            }

            for (String path : binaryTreePaths(root.right)) {
                paths.add(root.val + "->" + path);
            }

            return paths;
        }
    }

    private static class Recursive implements Method {

        @Override
        public List<String> binaryTreePaths(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<String> res = new ArrayList<>();
            traversal(root, new StringBuilder(), res);
            return res;
        }

        private void traversal(TreeNode root, StringBuilder sb, List<String> res) {
            int len = sb.length();
            if (len != 0) {
                sb.append("->");
            }
            sb.append(root.val);

            if (root.left == null && root.right == null) {
                res.add(sb.toString());
            }
            if (root.left != null) {
                traversal(root.left, sb, res);
            }
            if (root.right != null) {
                traversal(root.right, sb, res);
            }
            sb.setLength(len);
        }
    }
}
