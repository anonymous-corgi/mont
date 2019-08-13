package leetcode.p101to150;

import basicclass.ListNode;

public class LeetCode148SortList {

    private interface Method {
        ListNode sortList(ListNode head);
    }

    private static class MergeSort implements Method {

        @Override
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = findMid(head);
            ListNode right = sortList(mid.next);
            mid.next = null;
            ListNode left = sortList(head);

            return merge(left, right);
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
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
            if (left != null) {
                tail.next = left;
            } else {
                tail.next = right;
            }
            return dummy.next;
        }
    }

    private static class QuickSort implements Method {

        private ListNode mLastNode;

        @Override
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            return quickSort(head);
        }

        private ListNode quickSort(ListNode head) {
            if (head == null || head.next == null) {
                mLastNode = head;
                return head;
            }

            ListNode[] dummy = new ListNode[3];
            ListNode[] tail = new ListNode[3];
            for (int i = 0; i < 3; i++) {
                dummy[i] = new ListNode(-1);
                tail[i] = dummy[i];
            }

            int pivot = head.val;
            while (head != null) {
                if (head.val < pivot) {
                    tail[0].next = head;
                    tail[0] = tail[0].next;
                } else if (head.val == pivot) {
                    tail[1].next = head;
                    tail[1] = tail[1].next;
                } else {
                    tail[2].next = head;
                    tail[2] = tail[2].next;
                }
                head = head.next;
            }

            for (int i = 0; i < 3; i++) {
                tail[i].next = null;
            }

            ListNode newDummy = new ListNode(-1);
            ListNode newTail = newDummy;
            if (dummy[0].next != null) {
                newTail.next = quickSort(dummy[0].next);
                newTail = mLastNode;
            }
            newTail.next = dummy[1].next;
            newTail = tail[1];
            if (dummy[2].next != null) {
                newTail.next = quickSort(dummy[2].next);
                newTail = mLastNode;
            }

            mLastNode = newTail;
            return newDummy.next;
        }
    }

    private static class QuickSort2 implements Method {

        @Override
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode lastTail = quickSort(head);
            head = lastTail.next;
            lastTail.next = null;

            return head;
        }

        // Return the tail of the ListNode, meanwhile the next of the tailNode is the head.
        private ListNode quickSort(ListNode head) {
            if (head.next == null) {
                head.next = head;
                return head;
            }

            ListNode[] dummy = new ListNode[3];
            ListNode[] tail = new ListNode[3];
            for (int i = 0; i < 3; i++) {
                dummy[i] = new ListNode(-1);
                tail[i] = dummy[i];
            }

            int pivot = head.val;
            while (head != null) {
                if (head.val < pivot) {
                    tail[0].next = head;
                    tail[0] = tail[0].next;
                } else if (head.val == pivot) {
                    tail[1].next = head;
                    tail[1] = tail[1].next;
                } else {
                    tail[2].next = head;
                    tail[2] = tail[2].next;
                }
                head = head.next;
            }

            for (int i = 0; i < 3; i++) {
                tail[i].next = null;
            }

            ListNode newDummy = new ListNode(-1);
            ListNode newTail = newDummy;
            if (dummy[0].next != null) {
                ListNode lastTail = quickSort(dummy[0].next);
                newTail.next = lastTail.next;
                newTail = lastTail;
                lastTail.next = null;
            }
            newTail.next = dummy[1].next;
            newTail = tail[1];
            if (dummy[2].next != null) {
                ListNode lastTail = quickSort(dummy[2].next);
                newTail.next = lastTail.next;
                newTail = lastTail;
                lastTail.next = null;
            }

            newTail.next = newDummy.next;
            return newTail;
        }
    }
}
