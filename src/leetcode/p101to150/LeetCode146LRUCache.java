package leetcode.p101to150;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode146LRUCache {
  
  interface LRUCache {
    int get(int key);
    void put(int key, int value);
  }
  
  public static class DoubleLinkedList_method implements LRUCache {
    
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
    
    public DoubleLinkedList_method(int capacity) {
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
    
  }
  
  
  public static class LinkedHashMap_method1 implements LRUCache {
    
    private final int CAPACITY;
    private final LinkedHashMap<Integer,Integer> map;
    
    public LinkedHashMap_method1(int capacity) {
      this.CAPACITY = capacity;
      this.map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
      Integer value = this.map.get(key);
      if (value == null) {
        value = -1;
      } else {
        this.put(key, value);
      }
      return value;
    }
    
    public void put(int key, int value) {
      if (this.map.containsKey(key)) {
        this.map.remove(key);
      } else if (this.map.size() == this.CAPACITY) {
        Iterator<Integer> it = this.map.keySet().iterator();
        it.next();
        it.remove();
      }
      map.put(key, value);
    }
    
  }
  
  
  public static class LinkedHashMap_method2 implements LRUCache {
    
    private final int CAPACITY;
    private final LinkedHashMap<Integer,Integer> map;
    
    @SuppressWarnings("serial")
    public LinkedHashMap_method2(int capacity) {
      this.CAPACITY = capacity;
      this.map =  new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
          return size() > CAPACITY;
        }
      };
    }
    
    public int get(int key) {
      return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
      map.put(key, value);
    }
    
  }
  
}
