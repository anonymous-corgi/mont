package algorithm.classic.sort;

import algorithm.base.ListNode;

interface Sort {

    interface Array {
        void sort(int[] nums);
    }

    interface LinkedList {
        ListNode sort(ListNode head);
    }
}
