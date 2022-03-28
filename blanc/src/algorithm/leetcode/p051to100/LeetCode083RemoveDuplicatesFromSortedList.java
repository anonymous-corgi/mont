package algorithm.leetcode.p051to100;

import algorithm.base.ListNode;

public class LeetCode083RemoveDuplicatesFromSortedList {
  
  public ListNode deleteDuplicates(ListNode head) {
    ListNode slow = head;
    ListNode fast;
    while (slow != null) {
      fast = slow.next;
      while (fast != null && slow.val == fast.val) {
        fast = fast.next;
        slow.next.next = null;
        slow.next = fast;
      }
      slow = fast;
    }
    return head;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
