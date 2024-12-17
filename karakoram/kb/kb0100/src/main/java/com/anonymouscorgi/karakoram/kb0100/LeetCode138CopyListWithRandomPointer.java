package com.anonymouscorgi.karakoram.kb0100;

import java.util.HashMap;

/**
 * LeetCode 138. Copy List with Random Pointer
 */
interface LeetCode138CopyListWithRandomPointer {

  class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  Node copyRandomList(Node head);

  LeetCode138CopyListWithRandomPointer Method = new LeetCode138CopyListWithRandomPointer() {

    @Override
    public Node copyRandomList(Node head) {
      return copyRandomList(head, new HashMap<>());
    }

    private static Node copyRandomList(Node head, HashMap<Node, Node> cache) {
      if (head == null) {
        return null;
      }
      Node newHead = cache.computeIfAbsent(head, k -> new Node(k.val));
      newHead.next = copyRandomList(head.next, cache);
      newHead.random = head.random != null ?
          cache.computeIfAbsent(head.random, k -> new Node(k.val)) : null;

      return newHead;
    }
  };
}
