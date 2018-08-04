package leetcode.p151to200;

import java.util.Stack;
import basicclass.TreeNode;

public class LeetCode173BinarySearchTreeIterator {
  
  private Stack<TreeNode> items;
  
  public LeetCode173BinarySearchTreeIterator(TreeNode root) {
    items = new Stack<TreeNode>();
    while (root != null) {
      items.push(root);
      root = root.left;
    }
  }
  
  public boolean hasNext() {
    return !items.isEmpty();
  }
  
  public int next() {
    TreeNode cur = items.pop();
    TreeNode node = cur.right;
    while (node != null) {
      items.push(node);
      node = node.left;
    }
    return cur.val;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
