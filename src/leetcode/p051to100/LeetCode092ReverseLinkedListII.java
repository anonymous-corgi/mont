package leetcode.p051to100;

import algorithm.base.ListNode;

public class LeetCode092ReverseLinkedListII {
  
  public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;
    for (int i = 1; i < m; i++) {
      head = head.next;
    }        
    ListNode last = null;
    ListNode cursor = head.next;
    ListNode tail = head.next;
    for (int i = m; i <= n; i++) {
      ListNode temp = cursor.next;
      cursor.next = last;
      last = cursor;
      cursor = temp;
    }
    tail.next = cursor;
    head.next = last;
    return dummy.next;
  }

}
