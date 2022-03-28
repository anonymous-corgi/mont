package algorithm.leetcode.p101to150;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import algorithm.base.TreeNode;

public class LeetCode103BinaryTreeZigzagLevelOrderTraversal {

    private interface Method {
        List<List<Integer>> zigzagLevelOrder(TreeNode root);
    }

    private static class ZigzagTraversal implements Method {

        @Override
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> taskQueue = new ArrayDeque<>();
            taskQueue.offer(root);
            boolean isLeftToRight = true;
            while (!taskQueue.isEmpty()) {
                int len = taskQueue.size();
                List<Integer> level = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    if (isLeftToRight) {
                        TreeNode cursor = taskQueue.pollFirst();
                        level.add(cursor.val);
                        if (cursor.left != null) {
                            taskQueue.offerLast(cursor.left);
                        }
                        if (cursor.right != null) {
                            taskQueue.offerLast(cursor.right);
                        }
                    } else {
                        TreeNode cursor = taskQueue.pollLast();
                        level.add(cursor.val);
                        if (cursor.right != null) {
                            taskQueue.offerFirst(cursor.right);
                        }
                        if (cursor.left != null) {
                            taskQueue.offerFirst(cursor.left);
                        }
                    }
                }
                isLeftToRight = !isLeftToRight;
                res.add(level);
            }
            return res;
        }
    }
}
