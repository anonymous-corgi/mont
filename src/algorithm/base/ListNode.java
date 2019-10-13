package base;

public class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int x) { 
			val = x; 
		}
		public ListNode(int x, ListNode n){
			val = x;
			next = n;
		}
		
		public static ListNode generateList(int[] arr){
			if(arr == null || arr.length == 0){
				return null;
			}
			ListNode prevNode = null;
			for(int i = arr.length - 1; i >= 0; i--){
				ListNode one = new ListNode(arr[i], prevNode);
				prevNode = one;
			}
			return prevNode;
		}
		
		public String toString(){
			StringBuilder str = new StringBuilder();
			
			str.append(Integer.toString(val));
			ListNode nextNode = this.next;
			while(nextNode != null){
				str.append("->" + Integer.toString(nextNode.val));
				nextNode = nextNode.next;
			}
			return str.toString();
		}
}
