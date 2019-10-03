package leetcode.p301to350;

import basicclass.ListNode;

public class LeetCode328OddEvenLinkedList {

    private interface Method {
        ListNode oddEvenList(ListNode head);
    }

    private static final class TwoPointers implements Method {

        public ListNode oddEvenList(ListNode head) {
            ListNode evenDummy = new ListNode(-1);
            ListNode evenTail = evenDummy;
            evenDummy.next = head;
            ListNode oddDummy = new ListNode(-1);
            ListNode oddTail = oddDummy;
            while (evenTail != null && evenTail.next != null) {
                oddTail.next = evenTail.next;
                oddTail = oddTail.next;
                evenTail.next = evenTail.next.next;
                evenTail = evenTail.next;
            }
            oddTail.next = evenDummy.next;
            return oddDummy.next;
        }
    }
}
