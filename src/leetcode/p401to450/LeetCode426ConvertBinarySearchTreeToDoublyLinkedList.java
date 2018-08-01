package leetcode.p401to450;

import basicclass.DoublyListNode;
import basicclass.TreeNode;

public class LeetCode426ConvertBinarySearchTreeToDoublyLinkedList {

  private DoublyListNode tail;
  
  public DoublyListNode bstToDoublyList(TreeNode root) {
      // write your code here
      if (root == null) {
          return null;
      }
      DoublyListNode dummy = new DoublyListNode(-1);
      tail = dummy;
      helper(root);
      dummy = dummy.next;
      dummy.prev.next = null;
      dummy.prev = null;
      return dummy;
  }
  
  private void helper(TreeNode root) {
      if (root == null) {
          return;
      }
      helper(root.left);
      addToTail(new DoublyListNode(root.val));
      helper(root.right);
  }
  
  private void addToTail(DoublyListNode node) {
      tail.next = node;
      node.prev = tail;
      tail = node;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
