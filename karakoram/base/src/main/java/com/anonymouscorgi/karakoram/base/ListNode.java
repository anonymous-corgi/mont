package com.anonymouscorgi.karakoram.base;

/**
 * A class representing a node in a singly linked list.
 */
public class ListNode {

    /**
     * Converts an integer array to a linked list.
     *
     * @param arr the integer array to convert
     * @return the head of the linked list
     */
    public static ListNode fromIntArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode back = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            ListNode front = new ListNode(arr[i]);
            front.next = back;
            back = front;
        }
        return back;
    }

    public int val;
    public ListNode next;

    /**
     * Constructs a ListNode with the given value.
     */
    public ListNode(int val) {
        this.val = val;
    }

    /**
     * Returns a string representation of the linked list starting from this node.
     *
     * @return a string representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(val);
        ListNode nextNode = this.next;
        while (nextNode != null) {
            str.append("->").append(nextNode.val);
            nextNode = nextNode.next;
        }
        return str.toString();
    }
}
