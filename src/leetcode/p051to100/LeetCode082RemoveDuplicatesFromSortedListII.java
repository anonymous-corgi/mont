package leetcode.p051to100;

import basicclass.ListNode;

public class LeetCode082RemoveDuplicatesFromSortedListII {
  
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(-1); 
    ListNode tail = dummy;
    dummy.next = head;
    ListNode fast = tail.next;
    while (fast != null) {
      if (fast.next == null 
          || fast.next.val != fast.val) {
        tail.next = fast;
        tail = tail.next;
        fast = fast.next;
      } else {
        int val = fast.val;
        while (fast != null && fast.val == val) {
          fast = fast.next;
        }
      }
    }
    tail.next = null;
    return dummy.next;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
