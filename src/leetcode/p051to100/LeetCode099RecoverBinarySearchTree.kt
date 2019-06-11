package leetcode.p051to100

import basicclass.TreeNode

object LeetCode099RecoverBinarySearchTree {

    internal interface RecoverBinarySearchTree {
        fun recoverTree(root: TreeNode)
    }

    internal class DFS_Solution : RecoverBinarySearchTree {

        private var last: TreeNode? = null
        private var head: TreeNode? = null
        private var tail: TreeNode? = null

        override fun recoverTree(root: TreeNode) {
            dfs(root)
            val temp = head!!.`val`
            head!!.`val` = tail!!.`val`
            tail!!.`val` = temp
        }

        private fun dfs(root: TreeNode?) {
            if (root == null) {
                return
            }

            dfs(root.left)
            if (last != null && last!!.`val` > root.`val`) {
                if (head == null) {
                    head = last
                }
                tail = root
            }
            last = root
            dfs(root.right)
        }
    }

    internal class NonRecursive_Solution : RecoverBinarySearchTree {

        override fun recoverTree(root: TreeNode) {
            val stack = mutableListOf<TreeNode>()
            var cursor: TreeNode? = root
            var last: TreeNode? = null
            var head: TreeNode? = null
            var tail: TreeNode? = null
            while (cursor != null || !stack.isEmpty()) {
                if (cursor != null) {
                    stack.add(cursor)
                    cursor = cursor.left
                } else {
                    cursor = stack.removeAt(stack.lastIndex)
                    if (last != null && last.`val` > cursor.`val`) {
                        if (head == null) {
                            head = last
                        }
                        tail = cursor
                    }
                    last = cursor
                    cursor = cursor.right
                }
            }

            val temp = head!!.`val`
            head.`val` = tail!!.`val`
            tail.`val` = temp
        }
    }
}
