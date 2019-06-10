package jiuzhang.c3.traversal

import java.util.LinkedList
import java.util.Stack

import basicclass.TreeNode
import utils.Print

object Traversal {

    interface Traversal {
        fun traversal(root: TreeNode?): List<Int>
    }

    // In-Order InOrder Recursive //////////////////////////////////////////////////////////////////
    class InOrderRecursive : Traversal {

        override fun traversal(root: TreeNode?): List<Int> {
            val result = mutableListOf<Int>()
            if (root == null) return result

            inOrder(root, result)
            return result
        }

        private fun inOrder(root: TreeNode?, result: MutableList<Int>) {
            if (root == null) return

            inOrder(root.left, result)
            result.add(root.`val`)
            inOrder(root.right, result)
        }
    }


    // In-Order InOrder Non-Recursive //////////////////////////////////////////////////////////////
    class InOrder : Traversal {

        override fun traversal(root: TreeNode?): List<Int> {
            var cursor = root
            val res = mutableListOf<Int>()
            val stack = Stack<TreeNode>()

            while (!stack.isEmpty() || cursor != null) {
                if (cursor != null) {
                    stack.push(cursor)
                    cursor = cursor.left
                } else {
                    res.add(stack.peek().`val`)
                    cursor = stack.pop().right
                }
            }
            return res
        }
    }


    // Pre-Order PreOrder Non-Recursive ////////////////////////////////////////////////////////////
    class PreOrder : Traversal {

        override fun traversal(root: TreeNode?): List<Int> {
            var cursor = root
            val res = mutableListOf<Int>()
            val stack = Stack<TreeNode>()

            while (!stack.isEmpty() || cursor != null) {
                if (cursor != null) {
                    res.add(cursor.`val`)
                    stack.push(cursor)
                    cursor = cursor.left
                } else {
                    cursor = stack.pop().right
                }
            }
            return res
        }
    }


    // Post-Order PostOrder Non-Recursive //////////////////////////////////////////////////////////
    class PostOrder : Traversal {

        override fun traversal(root: TreeNode?): List<Int> {
            var cursor = root
            val res = mutableListOf<Int>()
            val stack = Stack<TreeNode>()
            var prev: TreeNode? = null

            while (!stack.isEmpty() || cursor != null) {
                if (cursor != null) {
                    stack.push(cursor)
                    cursor = cursor.left
                } else {
                    if (stack.peek().right != null && stack.peek().right !== prev) {
                        cursor = stack.peek().right
                    } else {
                        res.add(stack.peek().`val`)
                        prev = stack.pop()
                    }
                }
            }
            return res
        }
    }

    class PostOrder2 : Traversal {

        //Reverse of PreOrder Traversal
        override fun traversal(root: TreeNode?): List<Int> {
            var cursor = root
            val res = LinkedList<Int>()
            val stack = Stack<TreeNode>()

            while (!stack.isEmpty() || cursor != null) {
                if (cursor != null) {
                    res.addFirst(cursor.`val`)
                    stack.push(cursor)
                    //For PreOreder Traversal is: root = stack.pop());
                    cursor = cursor.right
                } else {
                    cursor = stack.pop().left
                }
            }
            return res
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val data = "[1,2,3,4,5,6,7]"
        val root = TreeNode.deserializeSortedArray(data)
        Print.printList(InOrder().traversal(root))
        Print.printList(PreOrder().traversal(root))
        Print.printList(PostOrder().traversal(root))
    }
}
