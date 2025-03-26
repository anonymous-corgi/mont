package com.anonymouscorgi.karakoram.kb1350;

import com.anonymouscorgi.karakoram.base.ListNode;
import com.anonymouscorgi.karakoram.base.TreeNode;

interface LeetCode1367LinkedListInBinaryTree {

  boolean isSubPath(ListNode head, TreeNode root);

  LeetCode1367LinkedListInBinaryTree METHOD =
      new LeetCode1367LinkedListInBinaryTree() {
        @Override
        public boolean isSubPath(ListNode head, TreeNode root) {
          return isSubPath(head, head, root);
        }

        private boolean isSubPath(ListNode head, ListNode next, TreeNode node) {
          if (node == null) {
            return false;
          }
          if (node.val == next.val) {
            next = next.next;
            if (next == null
                || isSubPath(null, next, node.left)
                || isSubPath(null, next, node.right)) {
              return true;
            }
          }
          if (head != null) {
            return isSubPath(head, head, node.left) || isSubPath(head, head, node.right);
          } else {
            return false;
          }
        }
      };
}
