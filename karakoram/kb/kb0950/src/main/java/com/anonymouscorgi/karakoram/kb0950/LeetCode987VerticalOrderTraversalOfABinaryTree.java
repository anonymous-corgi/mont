package com.anonymouscorgi.karakoram.kb0950;

import com.anonymouscorgi.karakoram.base.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * LeetCode 987. Vertical Order Traversal of a Binary Tree
 * <p>
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * <p>
 * For each node at position (row, col), its left and right children will be at positions (row + 1,
 * col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each
 * column index starting from the leftmost column and ending on the rightmost column. There may be
 * multiple nodes in the same row and same column. In such a case, sort these nodes by their
 * values.
 * <p>
 * Return the vertical order traversal of the binary tree.
 * <p>
 * Similar to
 * {@link com.anonymouscorgi.karakoram.kb0300.LeetCode314BinaryTreeVerticalOrderTraversal}
 */
interface LeetCode987VerticalOrderTraversalOfABinaryTree {

  List<List<Integer>> verticalTraversal(TreeNode root);

  LeetCode987VerticalOrderTraversalOfABinaryTree BFS = new LeetCode987VerticalOrderTraversalOfABinaryTree() {
    @Override
    public List<List<Integer>> verticalTraversal(TreeNode root) {
      if (root == null) {
        return Collections.emptyList();
      }

      HashMap<Integer, List<Integer>> resultMap = new HashMap<>();
      HashMap<Integer, List<Integer>> stagResultMap = new HashMap<>();
      LinkedList<Task> taskQueue = new LinkedList<>();
      taskQueue.offer(new Task(0, root));
      while (!taskQueue.isEmpty()) {
        int taskSize = taskQueue.size();
        for (int i = 0; i < taskSize; i++) {
          Task task = taskQueue.poll();
          List<Integer> stagColumnResult =
              stagResultMap.computeIfAbsent(task.column, k -> new ArrayList<>());
          stagColumnResult.add(task.treeNode.val);
          if (task.treeNode.left != null) {
            taskQueue.offer(new Task(task.column - 1, task.treeNode.left));
          }
          if (task.treeNode.right != null) {
            taskQueue.offer(new Task(task.column + 1, task.treeNode.right));
          }
        }
        for (Entry<Integer, List<Integer>> entry : stagResultMap.entrySet()) {
          List<Integer> columnResult =
              resultMap.computeIfAbsent(entry.getKey(), k -> new ArrayList<>());
          List<Integer> stagColumnResult = entry.getValue();
          Collections.sort(stagColumnResult);
          columnResult.addAll(stagColumnResult);
          stagColumnResult.clear();
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

    static class Task {

      int column;
      TreeNode treeNode;

      Task(int column, TreeNode treeNode) {
        this.treeNode = treeNode;
        this.column = column;
      }
    }
  };
}
