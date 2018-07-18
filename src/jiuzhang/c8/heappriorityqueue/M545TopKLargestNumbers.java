
package jiuzhang.c8.heappriorityqueue;
import java.util.ArrayList;
import java.util.List;

public class M545TopKLargestNumbers {
	
    public M545TopKLargestNumbers(int k) {
        // do intialization if necessary
        capacity = k;
        size = 0;
        // minValue = Integer.MIN_VALUE;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        if(size < capacity || num > tail.prev.value){
            Node one = new Node(num);
            Node current = head;
            for(int i = 0; i < capacity; i++){
                if(current.next == tail || current.next.value < num){
                    current.next.prev = one;
                    one.next = current.next;
                    current.next = one;
                    one.prev = current;
                    size++;
                    break;
                }
                current = current.next;
            }
            while(size > capacity){
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
                size--;
            }
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        // write your code here
        List<Integer> result = new ArrayList<>();
        Node current = head;
        while(current.next != tail){
            current = current.next;
            result.add(current.value);
        }
        return result;
    }
    
    private int capacity;
    private int size;
    private Node head;
    private Node tail;
    private class Node{
        int value;
        Node next;
        Node prev;
        Node(){
            this(0);
        }
        Node(int value){
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M545TopKLargestNumbers one = new M545TopKLargestNumbers(3);
		one.add(3);
		one.add(10);
		one.topk();
		one.add(1000);
		one.add(-99);
		one.topk();
		one.add(4);
		one.topk();
		one.add(100);
		one.topk();

	}

}
