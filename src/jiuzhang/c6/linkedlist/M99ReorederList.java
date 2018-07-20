package jiuzhang.c6.linkedlist;

import basicclass.ListNode;

public class M99ReorederList {
	
    public void reorderList(ListNode head) {
        // write your code here
        if(head == null){
            return;
        }
        
        ListNode current = head;
        ListNode mid = current;
        int num = 1;
        while(current != null){
            if(num % 2 == 0){
                mid = mid.next;
            }
            current = current.next;
            num++;
        }
        
        ListNode pre = null;
        ListNode cur = mid.next;
        mid.next = null;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        
        current = head;
        while(pre != null){
            ListNode temp = current.next;
            current.next = pre;
            pre = pre.next;
            current.next.next = temp;
            current = temp;
        }
        
        return;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2, 1, 0};
		ListNode head = ListNode.generateList(arr);
		M99ReorederList one = new M99ReorederList();
		System.out.println(head.toString());
		one.reorderList(head);
		System.out.println(head.toString());
	}

}
