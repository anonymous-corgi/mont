package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;

public class LeetCode146LRUCache {
  
  private static class Node {
    int key;
    int val;
    Node next;
    Node prev;
    public Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
  
  private final int CAPACITY;
  private final Node HEAD;
  private final Node END;
  private final Map<Integer, Node> MAP;
  private int size = 0;
  
  public LeetCode146LRUCache(int capacity) {
    this.CAPACITY = capacity;
    HEAD = new Node(0, -1);
    END = new Node(0, -1);
    HEAD.next = END;
    END.prev = HEAD;
    MAP = new HashMap<>();
  }
  
  public int get(int key) {
    if (!MAP.containsKey(key)) {
      return -1;
    }
    Node cursor = MAP.get(key);
    if (cursor.prev != HEAD) {
      addToFirst(extract(cursor));
    }
    return cursor.val;
  }
  
  public void put(int key, int value) {
    if (MAP.containsKey(key)) {
      Node cursor = MAP.get(key);
      if (cursor.prev != HEAD) {
        addToFirst(extract(cursor));
      }
      cursor.val = value;
    } else {
      Node cursor = new Node(key, value);
      addToFirst(cursor);            
      MAP.put(key, cursor);
      size++;
    }      
    if (size > CAPACITY) {
      Node rm = extract(END.prev);
      MAP.remove(rm.key);
      size--;
    }
  }
  
  private void addToFirst(Node node) {
    node.next = HEAD.next;
    HEAD.next.prev = node;
    node.prev = HEAD;
    HEAD.next = node;
  }
  
  private Node extract(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
    node.next = null;
    node.prev = null;
    return node;
  }

  public static void main(String[] args) {
    int cap = 1;
    LeetCode146LRUCache one =
        new LeetCode146LRUCache(cap);
    one.get(1);
    one.put(2, 1);
    one.get(1);
    
    
//    one.put(1, 1);
//    one.put(2, 2);
//    one.get(1);
//    one.put(3, 3);
//    one.get(1);
//    one.put(4, 4);
//    one.get(1);
//    one.get(3);
//    one.get(4);
  }

}
