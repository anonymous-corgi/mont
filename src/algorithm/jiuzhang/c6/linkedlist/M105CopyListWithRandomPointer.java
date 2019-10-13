package algorithm.jiuzhang.c6.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class M105CopyListWithRandomPointer {
	
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
            return head;
        }
        RandomListNode current = head;
        RandomListNode newCurrent = new RandomListNode(current.label);
        
        Map<RandomListNode, RandomListNode> record = new HashMap<>();
        record.put(current, newCurrent);
        
        while(current != null){
            
            RandomListNode nextNode = current.next;
            if(nextNode != null){
            	RandomListNode newNextNode;
            	if(record.containsKey(nextNode)){
            		newNextNode = record.get(nextNode);
            	}else{
            		newNextNode = new RandomListNode(nextNode.label);
            		record.put(nextNode, newNextNode);
            	}
            	newCurrent.next = newNextNode;
            }
            
            RandomListNode randomNode = current.random;
            if(randomNode != null){
            	RandomListNode newRandomNode;
            	if(record.containsKey(randomNode)){
            		newRandomNode = record.get(randomNode);
            	}else{
            		newRandomNode = new RandomListNode(randomNode.label);
            		record.put(randomNode, newRandomNode);
            	}
            	newCurrent.random = newRandomNode;
            }
            
            current = nextNode;
            newCurrent = newCurrent.next;
        }
        return record.get(head);
    }
    
    private class RandomListNode{
    	int label;
    	RandomListNode next;
    	RandomListNode random;
    	public RandomListNode(int x) {
			label = x;
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
