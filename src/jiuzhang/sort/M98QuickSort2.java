package jiuzhang.sort;

import basicclass.ListNode;

public class M98QuickSort2 {
	
    public ListNode sortList(ListNode head) {
        // write your code here
        if(head == null || head.next == null){
            return head;
        }
        return sort(head);
    }
    
    private ListNode lastTail;
    
    private ListNode sort(ListNode head) {
        if(head == null){
            return head;
        }
        
        int target = head.val;
        
        ListNode leftDummy = new ListNode(0); ListNode leftTail = leftDummy;
        ListNode middleDummy = new ListNode(0); ListNode middleTail = middleDummy;
        ListNode rightDummy = new ListNode(0); ListNode rightTail = rightDummy;
        
        while(head != null){
            if(head.val < target){
                leftTail.next = head;
                leftTail = leftTail.next;
            }else if(head.val > target){
                rightTail.next = head;
                rightTail = rightTail.next;
            }else {
                middleTail.next = head;
                middleTail = middleTail.next;
            }
            head = head.next;
        }
        
        leftTail.next = null; middleTail.next = null; rightTail.next = null;
        
        ListNode neoDummy = new ListNode(0); ListNode neoTail = neoDummy;
        
        if(leftDummy.next != null){
            neoTail.next = sort(leftDummy.next);
            neoTail = lastTail;
        }
            neoTail.next = middleDummy.next;
            neoTail = middleTail;
        if(rightDummy.next != null){
            neoTail.next = sort(rightDummy.next);
            neoTail = lastTail;
        }
            lastTail = neoTail;
        
        return neoDummy.next;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
