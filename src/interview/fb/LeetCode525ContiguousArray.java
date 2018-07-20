package interview.fb;

import basicclass.ListNode;

public class LeetCode525ContiguousArray {
	
	//LintCode994. Contiguous Array
	
    public ListNode reverse(ListNode head) {
        // write your code here
        if (head == null) {
            return null;
        }
        
        ListNode prev = null;
        ListNode nextNode = head.next;
        while (nextNode != null) {
            head.next = prev;
            prev = head;
            head = nextNode;
            nextNode = nextNode.next;
        }
        head.next = prev;
        return head;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
