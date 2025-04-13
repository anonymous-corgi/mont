package com.anonymouscorgi.karakoram.kb1350

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium
import com.anonymouscorgi.karakoram.base.ListNode
import com.anonymouscorgi.karakoram.base.TreeNode

@Medium
internal interface LeetCode1367LinkedListInBinaryTree {

  fun isSubPath(head: ListNode?, root: TreeNode?): Boolean

  companion object {

    val METHOD = object : LeetCode1367LinkedListInBinaryTree {
      override fun isSubPath(head: ListNode?, root: TreeNode?): Boolean {
        return isSubPath(head, head, root)
      }

      private fun isSubPath(head: ListNode?, next: ListNode?, node: TreeNode?): Boolean {
        var next = next
        if (node == null) {
          return false
        }
        if (node.`val` == next!!.`val`) {
          next = next.next
          if (next == null || isSubPath(null, next, node.left) || isSubPath(
              null, next, node.right
            )
          ) {
            return true
          }
        }

        return if (head != null) {
          isSubPath(head, head, node.left) || isSubPath(head, head, node.right)
        } else {
          false
        }
      }
    }

    @Accepted
    val METHOD2 = object : LeetCode1367LinkedListInBinaryTree {
      override fun isSubPath(head: ListNode?, root: TreeNode?): Boolean {
        return isSubPath(head, true, root)
      }

      private fun isSubPath(listNode: ListNode?, isHead: Boolean, treeNode: TreeNode?): Boolean {
        if (listNode == null) {
          return true
        } else if (treeNode == null) {
          return false
        }

        if (listNode.`val` == treeNode.`val`
            && (isSubPath(listNode.next, false, treeNode.left)
            || isSubPath(listNode.next, false, treeNode.right))
        ) {
          return true
        }

        return if (isHead) {
          isSubPath(listNode, true, treeNode.left) || isSubPath(
            listNode, true, treeNode.right
          )
        } else {
          false
        }
      }
    }
  }
}
