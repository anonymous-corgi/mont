package leetcode.p701to750;

import algorithm.base.ListNode;

/**
 * Given a node from a cyclic linked list which has been sorted,
 * write a function to insert a value into the list
 * such that it remains a cyclic sorted list.
 * The given node can be any single node in the list. Return the inserted new node.
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input:
 * 3->5->1
 * 4
 * Output:
 * 5->1->3->4
 * Example 2:
 * <p>
 * Input:
 * 2->2->2
 * 3
 * Output:
 * 3->2->2->2
 * Notice
 * 3->5->1 is a cyclic list, so 3 is next node of 1.
 * 3->5->1 is same with 5->1->3
 */
public class LeetCode708InsertIntoACyclicSortedList {

    public ListNode insert(ListNode node, int x) {
        ListNode cursor = node;
        ListNode newNode = new ListNode(x);
        if (cursor == null) {
            newNode.next = newNode;
        } else {
            do {
                // When node and node.next are ascending.
                if (cursor.val <= x && x <= cursor.next.val) {
                    break;
                }
                // When node is max and node.next is min.
                if (cursor.val > cursor.next.val) {
                    // When x is smaller than min or x is larger than max.
                    if (cursor.val <= x || x <= cursor.next.val) {
                        break;
                    }
                }
                cursor = cursor.next;
            } while (cursor != node);
            newNode.next = cursor.next;
            cursor.next = newNode;
        }
        return newNode;
    }
}
