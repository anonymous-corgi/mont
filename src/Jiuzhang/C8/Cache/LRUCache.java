package Jiuzhang.C8.Cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
    public LRUCache(int capacity) {
        // do intialization if necessary
        size = 0;
        this.capacity = capacity;
        stub = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (stub.containsKey(key)) {
            Node current = stub.get(key);
            if (current.prev != head) {
                extract(current);
                insertNext(current, head);
            }
            return current.val;
        } else {
            return -1;
        }
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
            Node neoNode = new Node(key, value);
            stub.put(key, neoNode);
            insertNext(neoNode, head);
            if (size == capacity) {
                stub.remove(tail.prev.key);
                extract(tail.prev);
                size--;
            }
            size++;
        }
    }
    
    private int size;
    private int capacity;
    private Node head, tail;
    private Map<Integer, Node> stub;
    
    private class Node {
        private int key, val;
        private Node next, prev;
        Node() {
            this(-1, 0, null, null);
        }
        Node(int k, int v) {
            this(k, v, null, null);
        }
        Node(int k, int v, Node p, Node n) {
            key = k;
            val = v;
            prev = p;
            next = n;
        }
    }
    
    private void extract(Node node) {
         node.next.prev = node.prev;
         node.prev.next = node.next;
         node.next = null;
         node.prev = null;
    }
    
    private void insertNext(Node node, Node refer) {
        node.prev = refer;
        node.next = refer.next;
        refer.next.prev = node;
        refer.next = node;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache one = new LRUCache(2);
		one.set(2, 1);
		one.set(1, 1);
		one.get(2);
		one.set(4, 1);
		one.get(1);
		one.get(2);
	}

}
