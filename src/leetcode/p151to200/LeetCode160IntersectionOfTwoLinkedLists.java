package leetcode.p151to200;

import basicclass.ListNode;

public class LeetCode160IntersectionOfTwoLinkedLists {

    private interface Method {
        ListNode getIntersectionNode(ListNode headA, ListNode headB);
    }

    private static final class TwoPointers implements Method {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = getLength(headA);
            int lenB = getLength(headB);
            while (lenA > lenB) {
                headA = headA.next;
                lenA--;
            }
            while (lenA < lenB) {
                headB = headB.next;
                lenB--;
            }
            while (headA != headB && headA != null) {
                headA = headA.next;
                headB = headB.next;
            }
            return headA;
        }

        private int getLength(ListNode node) {
            int len = 0;
            while (node != null) {
                len++;
                node = node.next;
            }
            return len;
        }
    }
}