package leetcode.p101to150;

import basicclass.TreeNode;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class LeetCode102BinaryTreeLevelOrderTraversal {

  interface Method {
    List<List<Integer>> levelOrder(TreeNode root);
  }

  public static class BFS_method implements Method {

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      if (root == null) {
        return res;
      }
      Queue<TreeNode> taskList = new ArrayDeque<>();
      taskList.offer(root);
      while (!taskList.isEmpty()) {
        List<Integer> levelRes = new ArrayList<>();
        for (int i = 0, iSize = taskList.size(); i < iSize; i++) {
          TreeNode cursor = taskList.poll();
          levelRes.add(cursor.val);
          if (cursor.left != null) {
            taskList.offer(cursor.left);
          }
          if (cursor.right != null) {
            taskList.offer(cursor.right);
          }
        }
        res.add(levelRes);
      }
      return res;
    }

  }

  public static class Recursive_method implements Method {

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
      return levelOrder(root, 0, new ArrayList<>());
    }

    private List<List<Integer>> levelOrder(TreeNode root, int depth, List<List<Integer>> list) {
      if (root == null) {
        return list;
      }
      if (depth >= list.size()) {
        list.add(new ArrayList<Integer>());
      }
      list.get(depth).add(root.val);

      levelOrder(root.left, depth + 1, list);
      levelOrder(root.right, depth + 1, list);

      return list;
    }

  }

}
