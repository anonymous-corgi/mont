package algorithm.jiuzhang.c6.cyclicList;

import algorithm.base.ListNode;

public class E599InsertIntoACyclicSortedList {

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
}
