package com.anonymouscorgi.karakoram.kb0650;

import com.anonymouscorgi.karakoram.base.ListNode;

/**
 * LintCode 599. Insert into a Cyclic Sorted List
 *
 * Given a node from a cyclic linked list which has been sorted, write a function to insert a value
 * into the list such that it remains a cyclic sorted list. The given node can be any single node in
 * the list. Return the inserted new node.
 */
interface LintCode599InsertIntoACyclicSortedList {

  ListNode insert(ListNode node, int x);

  LintCode599InsertIntoACyclicSortedList Method = new LintCode599InsertIntoACyclicSortedList() {
    @Override
    public ListNode insert(ListNode node, int x) {
      ListNode cursor = node;
      ListNode newNode = new ListNode(x);
      if (cursor == null) {
        newNode.next = newNode;
      } else {
        do {
          // When node and node.next are ascending.
          if (cursor.val <= x && x <= cursor.next.val) {
            break;
          }
          // When node is max and node.next is min.
          if (cursor.val > cursor.next.val) {
            // When x is smaller than min or x is larger than max.
            if (cursor.val <= x || x <= cursor.next.val) {
              break;
            }
          }
          cursor = cursor.next;
        } while (cursor != node);
        newNode.next = cursor.next;
        cursor.next = newNode;
      }
      return newNode;
    }
  };
}
