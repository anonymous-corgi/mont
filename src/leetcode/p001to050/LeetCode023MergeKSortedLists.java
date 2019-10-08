package leetcode.p001to050;

import basicclass.ListNode;

public class LeetCode023MergeKSortedLists {

    private interface Method {
        ListNode mergeKLists(ListNode[] lists);
    }

    private static final class Binary implements Method {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            return mergeSort(lists, 0, lists.length - 1);
        }

        private ListNode mergeSort(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }
            int mid = start + (end - start) / 2;
            ListNode left = mergeSort(lists, start, mid);
            ListNode right = mergeSort(lists, mid + 1, end);
            return merge(left, right);
        }

        private ListNode merge(ListNode left, ListNode right) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    tail.next = left;
                    left = left.next;
                } else {
                    tail.next = right;
                    right = right.next;
                }
                tail = tail.next;
            }
            tail.next = left != null ? left : right;
            return dummy.next;
        }
    }
}