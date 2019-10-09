package leetcode.p451to500;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LeetCode460LFUCache {

    private static abstract class LFUCache {

        public LFUCache(int capacity) {
        }

        public abstract int get(int key);

        public abstract void put(int key, int value);
    }

    private static final class LinkedListMap extends LFUCache {

        private static final class Node {
            int freq;
            Node prev;
            Node next;
            Map<Integer, Integer> keys = new LinkedHashMap<>(0, 0.75f, true);

            Node(int freq) {
                this.freq = freq;
            }
        }

        private final int CAPACITY;
        private final Node HEAD;
        private final Node TAIL;
        private final Map<Integer, Node> map;
        private int size = 0;

        public LinkedListMap(int capacity) {
            super(capacity);
            CAPACITY = capacity;
            HEAD = new Node(0);
            TAIL = new Node(0);
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
            map = new HashMap<>();
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            int val = node.keys.get(key);
            put(key, val);
            return val;
        }

        public void put(int key, int value) {
            if (CAPACITY == 0) {
                return;
            }

            Node oldNode = map.get(key);
            // First, evict the least frequency node if we get a new key and reach max capacity.
            if (oldNode == null && size++ == CAPACITY) {
                int keyToEvict = HEAD.next.keys.keySet().iterator().next();
                removeKeyFromNode(keyToEvict, HEAD.next);
                map.remove(keyToEvict);
                size--;
            }

            // Second, find or create newNode
            Node newNode;
            Node frontNode = oldNode != null ? oldNode : HEAD;
            if (frontNode.next.freq == frontNode.freq + 1) {
                newNode = frontNode.next;
            } else {
                newNode = new Node(frontNode.freq + 1);
                insert(newNode, frontNode);
            }
            // And then put key into newNode's keys.
            newNode.keys.put(key, value);

            // Third, determine if we need to update oldNode.
            oldNode = map.get(key);
            if (oldNode != null) {
                removeKeyFromNode(key, oldNode);
            }

            // Finally, update map.
            map.put(key, newNode);
        }

        private void removeKeyFromNode(int key, Node oldNode) {
            oldNode.keys.remove(key);
            if (oldNode.keys.isEmpty()) {
                extract(oldNode);
            }
        }

        private void insert(Node node, Node front) {
            node.next = front.next;
            front.next.prev = node;
            node.prev = front;
            front.next = node;
        }

        private void extract(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }

    private static LFUCache getMethod(int capacity) {
        return new LinkedListMap(capacity);
    }

    @Test
    public void testcase1() {
        LFUCache cache = getMethod(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), is(1));
        cache.put(3, 3);    // evicts key 2
        assertThat(cache.get(2), is(-1));
        assertThat(cache.get(3), is(3));
        cache.put(4, 4);    // evicts key 1.
        assertThat(cache.get(1), is(-1));
        assertThat(cache.get(3), is(3));
        assertThat(cache.get(4), is(4));
    }
}