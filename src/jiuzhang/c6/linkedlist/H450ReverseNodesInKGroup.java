package Jiuzhang.C6.LinkedList;

public class H450ReverseNodesInKGroup {	
	
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
	
	private ListNode reverseK(ListNode dummyHead, int k){
//		����ָ��ͷβ��ָ��&�������ԣ�n1��ֱ�ӵõ���nk��Ҫѭ���õ���
		ListNode n1 = dummyHead.next;
		ListNode nk = dummyHead;
		for(int i = 0; i < k; i++){
			if(nk.next == null){
				return null;
			}
			nk = nk.next;
		}
		
		ListNode prev = nk.next;
		ListNode current = n1;
		for(int i = 0; i < k; i++){
			ListNode temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
		dummyHead.next = nk;
		
		return n1;
	}
	
	
	public static void main(String[] args) {
		
	}

}
