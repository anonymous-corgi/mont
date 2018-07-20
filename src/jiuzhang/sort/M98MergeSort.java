package jiuzhang.sort;

import basicclass.ListNode;

public class M98MergeSort {
	
    public ListNode mergeSort(ListNode head) {
        if(head == null){
            return head;
        }
        return sort(head);
    }
    
    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode sort(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode middle= findMiddle(head);
        
        ListNode right = sort(middle.next);
        middle.next = null;
        ListNode left = sort(head);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(left != null && right != null){
            if(left.val < right.val){
                tail.next = left;
                left = left.next;
            }else{
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if(left == null){
            tail.next = right;
        }else if(right == null){
            tail.next = left;
        }
        return dummy.next;
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
