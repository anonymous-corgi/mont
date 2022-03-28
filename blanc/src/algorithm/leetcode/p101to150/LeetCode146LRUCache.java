package algorithm.leetcode.p101to150;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode146LRUCache {

    private interface LRUCache {

        int get(int key);

        void put(int key, int value);
    }

    public static final class DoubleLinkedList implements LRUCache {

        private static class Node {
            int key;
            int val;
            Node next;
            Node prev;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private final int CAPACITY;
        private final Node HEAD;
        private final Node TAIL;
        private final Map<Integer, Node> MAP;

        public DoubleLinkedList(int capacity) {
            this.CAPACITY = capacity;
            HEAD = new Node(0, -1);
            TAIL = new Node(0, -1);
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
            MAP = new HashMap<>();
        }

        public int get(int key) {
            Node cursor = MAP.get(key);
            if (cursor == null) {
                return -1;
            } else if (cursor.prev != HEAD) {
                extract(cursor);
                addToFirst(cursor);
            }
            return cursor.val;
        }

        public void put(int key, int value) {
            Node cursor = MAP.get(key);
            if (cursor == null) {
                cursor = new Node(key, value);
                addToFirst(cursor);
                MAP.put(key, cursor);
            } else {
                if (cursor.prev != HEAD) {
                    extract(cursor);
                    addToFirst(cursor);
                }
                cursor.val = value;
            }
            if (MAP.size() > CAPACITY) {
                Node staleNode = TAIL.prev;
                extract(staleNode);
                MAP.remove(staleNode.key);
            }
        }

        private void addToFirst(Node node) {
            insert(node, HEAD);
        }

        private void insert(Node node, Node front) {
            front.next.prev = node;
            node.next = front.next;
            front.next = node;
            node.prev = front;
        }

        private void extract(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }

    public static final class LinkedHashMap1 implements LRUCache {

        private final int CAPACITY;
        private final LinkedHashMap<Integer, Integer> map;

        public LinkedHashMap1(int capacity) {
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

    public static final class LinkedHashMap2 implements LRUCache {

        private final int CAPACITY;
        private final LinkedHashMap<Integer, Integer> map;

        public LinkedHashMap2(int capacity) {
            this.CAPACITY = capacity;
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
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
