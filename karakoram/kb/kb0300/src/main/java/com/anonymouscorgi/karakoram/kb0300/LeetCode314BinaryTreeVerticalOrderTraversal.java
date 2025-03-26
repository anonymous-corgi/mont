package com.anonymouscorgi.karakoram.kb0300;

import com.anonymouscorgi.karakoram.base.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 314. Binary Tree Vertical Order Traversal
 *
 * <p>Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top
 * to bottom, column by column).
 *
 * <p>If two nodes are in the same row and column, the order should be from left to right.
 *
 * <p>Similar to {@link
 * com.anonymouscorgi.karakoram.kb0950.LeetCode987VerticalOrderTraversalOfABinaryTree}
 */
interface LeetCode314BinaryTreeVerticalOrderTraversal {

  List<List<Integer>> verticalTraversal(TreeNode root);

  LeetCode314BinaryTreeVerticalOrderTraversal BFS =
      new LeetCode314BinaryTreeVerticalOrderTraversal() {

        @Override
        public List<List<Integer>> verticalTraversal(TreeNode root) {
          if (root == null) {
            return Collections.emptyList();
          }

          HashMap<Integer, List<Integer>> resultMap = new HashMap<>();
          LinkedList<Task> taskQueue = new LinkedList<>();
          taskQueue.offer(new Task(0, root));
          while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            List<Integer> columnResult =
                resultMap.computeIfAbsent(task.column, k -> new ArrayList<>());
            columnResult.add(task.treeNode.val);
            if (task.treeNode.left != null) {
              taskQueue.offer(new Task(task.column - 1, task.treeNode.left));
            }
            if (task.treeNode.right != null) {
              taskQueue.offer(new Task(task.column + 1, task.treeNode.right));
            }
          }

          List<List<Integer>> result = new ArrayList<>();
          List<Integer> orderedKeys = new ArrayList<>(resultMap.keySet());
          Collections.sort(orderedKeys);
          for (int orderedKey : orderedKeys) {
            result.add(resultMap.get(orderedKey));
          }

          return result;
        }

        class Task {

          int column;
          TreeNode treeNode;

          Task(int column, TreeNode treeNode) {
            this.treeNode = treeNode;
            this.column = column;
          }
        }
      };
}
