package interview.linkedin;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FrequencyCounter {

    private interface Counter<K> {

        void increment(K key);

        void decrement(K key);

        K getMinKey();

        K getMaxKey();
    }

    private static final class LinkedListMap<K> implements Counter<K> {

        private static final class Node<K> {
            int freq;
            Node<K> prev;
            Node<K> next;
            Set<K> keys = new HashSet<>();

            Node(int freq) {
                this.freq = freq;
            }
        }

        private final Node<K> HEAD;
        private final Node<K> TAIL;
        private final Map<K, Node<K>> map;

        public LinkedListMap() {
            HEAD = new Node<>(0);
            TAIL = new Node<>(0);
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
            map = new HashMap<>();
        }

        public void increment(K key) {
            Node<K> newNode;
            Node<K> oldNode = map.get(key);
            Node<K> frontNode = oldNode != null ? oldNode : HEAD;
            // First, find or create newNode
            if (frontNode.next.freq != frontNode.freq + 1) {
                newNode = new Node<>(frontNode.freq + 1);
                insert(newNode, frontNode);
            } else {
                newNode = frontNode.next;
            }
            // And then put key into newNode's keys.
            newNode.keys.add(key);

            // Second, determine if we need to update oldNode.
            if (oldNode != null) {
                removeKeyFromNode(key, oldNode);
            }

            // Finally, update map.
            map.put(key, newNode);
        }

        public void decrement(K key) {
            Node<K> oldNode = map.get(key);
            if (oldNode == null) {
                return;
            }

            if (oldNode.freq != 1) {
                Node<K> newNode;
                if (oldNode.prev.freq != oldNode.freq - 1) {
                    newNode = new Node<>(oldNode.freq - 1);
                    insert(newNode, oldNode.prev);
                } else {
                    newNode = oldNode.prev;
                }
                newNode.keys.add(key);
                map.put(key, newNode);
            }

            removeKeyFromNode(key, oldNode);
        }

        public K getMinKey() {
            if (HEAD.next.freq == 0) {
                return null;
            }
            return HEAD.next.keys.iterator().next();
        }

        public K getMaxKey() {
            if (TAIL.prev.freq == 0) {
                return null;
            }
            return TAIL.prev.keys.iterator().next();
        }

        private void removeKeyFromNode(K key, Node node) {
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                extract(node);
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

    private static <K> Counter<K> getMethod() {
        return new LinkedListMap<>();
    }

    @Test
    public void testcase1() {
        Counter<String> counter = getMethod();

        counter.increment("one");
        assertThat(counter.getMinKey(), equalTo("one"));
        assertThat(counter.getMaxKey(), equalTo("one"));

        counter.increment("one");
        counter.increment("two");
        assertThat(counter.getMinKey(), equalTo("two"));
        assertThat(counter.getMaxKey(), equalTo("one"));

        counter.increment("two");
        counter.increment("two");
        assertThat(counter.getMinKey(), equalTo("one"));
        assertThat(counter.getMaxKey(), equalTo("two"));

        counter.decrement("two");
        counter.decrement("two");
        assertThat(counter.getMinKey(), equalTo("two"));
        assertThat(counter.getMaxKey(), equalTo("one"));

        counter.decrement("two");
        counter.increment("three");
        assertThat(counter.getMinKey(), equalTo("three"));
        assertThat(counter.getMaxKey(), equalTo("one"));
    }
}
