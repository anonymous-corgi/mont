package leetcode.p101to150;

import algorithm.base.ListNode;
import algorithm.base.TreeNode;

public class LeetCode109ConvertSortedListToBinarySearchTree {
  
  public static class DivideConquer_method {
    
    private ListNode cursor;
    public TreeNode sortedListToBST(ListNode head) {
      cursor = head;
      return build(1, count(head));
    }
    
    private int count(ListNode head) {
      int res = 0;
      while (head != null) {
        head = head.next;
        res++;
      }
      return res;
    }
    
    private TreeNode build(int start, int end) {
      TreeNode res = null;
      if (start <= end) {
        int mid = start + (end - start) / 2;
        TreeNode left = build(start, mid - 1);
        res = new TreeNode(cursor.val);
        cursor = cursor.next;
        TreeNode right = build(mid + 1, end);
        res.left = left;
        res.right = right;
      }
      return res;
    }
    
  }
  
  public static class TwoPointer_method {
    
    public TreeNode sortedListToBST(ListNode head) {
      return ListToBST(head, null);
    }
    
    private TreeNode ListToBST(ListNode head, ListNode tail) {
      if(head==tail) return null;
      ListNode fast = head, slow = head;
      
      while(fast!=tail && fast.next!=tail) {
        slow = slow.next;
        fast = fast.next.next;
      }
      
      TreeNode root = new TreeNode(slow.val);
      root.left = ListToBST(head, slow);
      root.right = ListToBST(slow.next, tail);
      return root;
    }
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
