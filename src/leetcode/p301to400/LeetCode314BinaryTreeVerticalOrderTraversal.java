package leetcode.p301to400;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import basicclass.TreeNode;
import utils.Print;

public class LeetCode314BinaryTreeVerticalOrderTraversal {
  
  private int lBias = 0;
  private int rBias = 0;
  
  public List<List<Integer>> verticalOrder(TreeNode root) {
    // write your code here
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    getBias(root, 0);
    for (int i = 0, len = rBias - lBias  + 1; i < len; i++) {
      results.add(new ArrayList<Integer>());
    }
    
    Queue<TreeNode> taskList = new LinkedList<>();
    Queue<Integer> posiList = new LinkedList<>();
    taskList.offer(root);
    posiList.offer(-lBias);
    while (!taskList.isEmpty()) {
      TreeNode cursor = taskList.poll();
      int posi = posiList.poll();
      results.get(posi).add(cursor.val);
      if (cursor.left != null) {
        taskList.offer(cursor.left);
        posiList.offer(posi - 1);
      }
      if (cursor.right != null) {
        taskList.offer(cursor.right);
        posiList.offer(posi + 1);
      }
    }
    return results;
  }
  
  private void getBias(TreeNode root, int posi) {
    if (root == null) {
      return;
    }
    lBias = Math.min(lBias, posi);
    rBias = Math.max(rBias, posi);
    getBias(root.left, posi - 1);
    getBias(root.right, posi + 1);
  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode314BinaryTreeVerticalOrderTraversal one =
        new LeetCode314BinaryTreeVerticalOrderTraversal();
    String treenodeString = "{3,9,20,#,#,15,7}";
    TreeNode root = TreeNode.deserialize(treenodeString);
    List<List<Integer>> list = one.verticalOrder(root);
    Print.printListList(list);
  }

}
