package leetcode.p001to050;

import basicclass.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 */
public class LeetCode025ReverseNodesInKGroup {

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        head = dummy;
        while (head != null) {
            head = reverseK(head, k);
        }

        return dummy.next;
    }

    private ListNode reverseK(ListNode dummyHead, int k) {
        // Memorize the dummyHead of the next k-group
        ListNode nextDummyHead = dummyHead.next;
        // Check it has at least k nodes.
        ListNode cursor = nextDummyHead;
        for (int i = 0; i < k; i++) {
            if (cursor == null) {
                return null;
            }
            cursor = cursor.next;
        }

        // The head of the reversed ListNode.
        ListNode reversedHead = cursor;
        // Memorize the original next of the cursor.
        ListNode nextNode = dummyHead.next;
        for (int i = 0; i < k; i++) {
            cursor = nextNode;
            nextNode = cursor.next;
            cursor.next = reversedHead;
            reversedHead = cursor;
        }
        dummyHead.next = cursor;

        return nextDummyHead;
    }
}
