package leetcode.p101to150

import basicclass.TreeNode
import java.util.*

class LeetCode103BinaryTreeZigzagLevelOrderTraversal {

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (root == null) {
            return res
        }

        var leftToRight = true
        val taskList = ArrayDeque<TreeNode>().apply { offer(root) }

        while (!taskList.isEmpty()) {
            val list = mutableListOf<Int>()
            for (i in taskList.indices) {
                if (leftToRight) {
                    taskList.pollFirst().apply {
                        list.add(this.`val`)
                        left?.let { n -> taskList.offerLast(n) }
                        right?.let { n -> taskList.offerLast(n) }
                    }
                } else {
                    taskList.pollLast().apply {
                        list.add(this.`val`)
                        right?.let { n -> taskList.offerFirst(n) }
                        left?.let { n -> taskList.offerFirst(n) }
                    }
                }
            }
            leftToRight = !leftToRight
            res.add(list)
        }
        return res
    }
}