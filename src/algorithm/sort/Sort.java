package algorithm.sort;

import basicclass.ListNode;

public interface Sort {

    interface Array {
        void sort(int[] nums);
    }

    interface LinkedList {
        ListNode sort(ListNode head);
    }
}
