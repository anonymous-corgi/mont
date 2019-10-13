package algorithm.jiuzhang.c6.linkedlist;

import algorithm.base.ListNode;

public class H450ReverseNodesInKGroup {

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(head == null || k <= 1){
    		return head;
    	}

    	ListNode dummy = new  ListNode(0);
    	dummy.next = head;

    	head = dummy;
    	while(head != null){
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
