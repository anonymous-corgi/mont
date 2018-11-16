package leetcode.p101to150

import basicclass.ListNode

class LeetCode148SortList {

    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val mid = findMid(head)

        val right: ListNode? = sortList(mid.next)
        mid.next = null
        val left: ListNode? = sortList(head)

        return merge(left, right)
    }

    private fun findMid(head: ListNode): ListNode {
        var slow = head
        var fast: ListNode? = head.next
        while (fast?.next != null) {
            fast = fast.next.next
            slow = slow.next
        }
        return slow
    }

    private fun merge(l: ListNode?, r: ListNode?): ListNode {
        val dummy = ListNode(0)
        var tail= dummy
        var left = l
        var right = r
        while (left != null && right != null) {
            if (left.`val` < right.`val`) {
                tail.next = left
                left = left.next
            } else {
                tail.next = right
                right = right.next
            }
            tail = tail.next
        }
        if (left != null) {
            tail.next = left
        }
        if (right != null) {
            tail.next = right
        }
        return dummy.next
    }
}