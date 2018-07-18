package Jiuzhang.C8.Cache;
import java.util.HashMap;
import java.util.Map;

public class H134LRUCache {

    public H134LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if(!stub.containsKey(key)){
            return -1;
        }
        Node current = stub.get(key);
        if(head.next != current){
            current.prev.next = current.next;
            current.next.prev = current.prev;
            moveToFirst(current);
        }
        return current.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if(get(key) == -1){            
            if(stub.size() >= capacity){
                stub.remove(tail.prev.key);
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
            }
            Node current = new Node(key, value);
            stub.put(key, current);
            moveToFirst(current);
        }else{
            stub.get(key).value = value;
        }
    }
    
    private int capacity;
    private Node head, tail;
    private Map<Integer, Node> stub = new HashMap<>();
    private class Node{
        int key;
        int value;
        Node prev, next;
        Node(int key, int value){
            this(key, value, null, null);
        }
        Node(int key, int value, Node prev, Node next){
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private void moveToFirst(Node n){
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
        n.prev = head;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		H134LRUCache one = new H134LRUCache(2);
		one.set(2, 1);
		one.set(1, 1);
		one.get(2);
		one.set(4, 1);
		one.get(1);
		one.get(2);
	}

}
