package jiuzhang.c8.cache;

import java.util.HashMap;
import java.util.Map;

public class H24LFUCache {
	
    public H24LFUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        stub = new HashMap<Integer, Node>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if (get(key) != -1) {
            stub.get(key).val = value;
        } else {
            if (stub.size() == capacity) {
                stub.remove(tail.prev.key);
                extract(tail.prev);
            }
            Node current = new Node(key, value, 1);
            stub.put(key, current);
            insertAhead(tail, current);
            adjustPos(current);
        }
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (stub.containsKey(key)) {
            Node current = stub.get(key);
            current.fre += 1;
            adjustPos(current);
            return current.val;
        }
        return -1;
    }
    private int capacity;
    private Node head;
    private Node tail;
    private Map<Integer, Node> stub;
    
    private class Node{
        int key;
        int val;
        int fre;
        Node prev;
        Node next;
        Node() {
            this(-1, -1, 0);
        }
        Node(int k, int v, int f) {
            key = k;
            val = v;
            fre = f;
            prev = null;
            next = null;
        }
    }
    
    private void extract(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void insertAhead(Node pos, Node node) {
        node.next = pos;
        node.prev = pos.prev;
        pos.prev.next = node;
        pos.prev = node;
    }
    private void adjustPos(Node node) {
        Node current = node;
        while (current.prev != head) {
            if (current.prev.fre > node.fre) {
                break;
            }
            current = current.prev;
        }
        if (current != node) {
            extract(node);
            insertAhead(current, node);
        }
    }
    
    public void printList() {
    	Node current = head;
    	int index = 1;
    	while (current.next != tail) {
    		System.out.print((index++) + " K:" + current.next.key + ", V:" + current.next.val + ", F:" + current.next.fre + ";\t");
    		current = current.next;
    	}
    	System.out.println();
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int capacity = 3;
		H24LFUCache one = new H24LFUCache(capacity);
		
		one.set(1, 10);
		one.printList();
		one.set(2, 20);
		one.set(3, 30);
		one.printList();
		one.get(1);
		one.printList();
		one.set(4, 40);
		one.printList();
		one.get(4);
		one.printList();
		one.get(3);
		one.printList();
		one.get(2);
		one.printList();
		one.get(1);
		one.printList();

	}

}
