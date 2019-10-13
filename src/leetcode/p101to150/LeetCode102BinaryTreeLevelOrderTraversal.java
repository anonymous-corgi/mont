package leetcode.p101to150;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import algorithm.base.TreeNode;

public class LeetCode102BinaryTreeLevelOrderTraversal {

    private interface Method {
        List<List<Integer>> levelOrder(TreeNode root);
    }

    private static class BFS implements Method {

        @Override
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> taskQueue = new ArrayDeque<>();
            taskQueue.offer(root);
            while (!taskQueue.isEmpty()) {
                int len = taskQueue.size();
                List<Integer> level = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    TreeNode cursor = taskQueue.poll();
                    level.add(cursor.val);
                    if (cursor.left != null) {
                        taskQueue.offer(cursor.left);
                    }
                    if (cursor.right != null) {
                        taskQueue.offer(cursor.right);
                    }
                }
                res.add(level);
            }
            return res;
        }
    }

    public static class Recursive implements Method {

        @Override
        public List<List<Integer>> levelOrder(TreeNode root) {
            return levelOrder(root, 0, new ArrayList<>());
        }

        private List<List<Integer>> levelOrder(TreeNode root, int depth, List<List<Integer>> res) {
            if (root == null) {
                return res;
            }
            if (depth >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(depth).add(root.val);

            levelOrder(root.left, depth + 1, res);
            levelOrder(root.right, depth + 1, res);

            return res;
        }
    }
}
