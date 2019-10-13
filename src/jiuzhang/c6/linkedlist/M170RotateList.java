package jiuzhang.c6.linkedlist;

import algorithm.base.ListNode;

public class M170RotateList {
	
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if(head == null){
            return head;
        }
        ListNode current = head;
        int num = 1;
        while(current.next != null){
            current = current.next;
            num++;
        }
        ListNode lastNode = current;
        
        int offset = num - (k % num);
        if(offset == num){
        	return head;
        }
        
        current = head;
        for(int i = 0; i < offset - 1; i++){
            current = current.next;
        }
        ListNode midHead = current.next;
        current.next =null;
        
        lastNode.next = head;
        return midHead;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,2,3,4,5};
		ListNode head = ListNode.generateList(arr);
		M170RotateList one = new M170RotateList();
		System.out.println(head.toString());
		head = one.rotateRight(head, 1);
		System.out.println(head.toString());

	}

}
