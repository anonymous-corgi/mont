package com.anonymouscorgi.karakoram.kb0450

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium
import com.anonymouscorgi.karakoram.base.TreeNode

@Medium
interface LeetCode450DeleteNodeInABST {

    fun deleteNode(root: TreeNode?, key: Int): TreeNode?

    @Accepted
    object METHOD : LeetCode450DeleteNodeInABST {

        override fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
            return when {
                root == null -> null
                key < root.`val` -> root.apply { left = deleteNode(left, key) }
                key > root.`val` -> root.apply { right = deleteNode(right, key) }
                else -> {
                    when {
                        root.right != null -> findMinNode(root.right).apply {
                            right = deleteNode(root.right, `val`)
                            left = root.left
                        }

                        root.left != null -> findMaxNode(root.left).apply {
                            left = deleteNode(root.left, `val`)
                            right = root.right
                        }

                        else -> null
                    }
                }
            }
        }

        private fun findMinNode(root: TreeNode): TreeNode =
            root.left?.let { findMinNode(it) } ?: root

        private fun findMaxNode(root: TreeNode): TreeNode =
            root.right?.let { findMinNode(it) } ?: root
    }
}