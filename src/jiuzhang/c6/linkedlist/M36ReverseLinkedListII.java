package jiuzhang.c6.linkedlist;

import algorithm.base.ListNode;

public class M36ReverseLinkedListII {
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode mDummy = new ListNode(0);
        ListNode mNode = null;
        
        int num = 0;
        while(head != null){
            head = head.next;
            if(num == m - 1){
                mNode = head;
                break;
            }
            num++;
        }
        ListNode lastNode = head;

        for(int i = m; i < n; i++){
            ListNode pre = mDummy.next;
            mDummy.next = head;
            head = head.next;
            mDummy.next.next = pre;
        }
        mNode.next = mDummy.next;
        lastNode.next = head;
        return dummy.next;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
