package com.anonymouscorgi.karakoram.kb1600

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium
import com.anonymouscorgi.karakoram.base.TreeNode

@Medium
internal interface LeetCode1609EvenOddTree {

  fun isEvenOddTree(root: TreeNode?): Boolean

  @Accepted
  object DFS : LeetCode1609EvenOddTree {

    override fun isEvenOddTree(root: TreeNode?): Boolean {
      return root?.run { isEvenOddTree(this, mutableListOf(), 0) } ?: true
    }

    fun isEvenOddTree(root: TreeNode, valList: MutableList<Int>, level: Int): Boolean {
      if (level.isEven()) {
        if (root.`val`.isEven()) {
          return false
        }
      } else {
        if (!root.`val`.isEven()) {
          return false
        }
      }

      if (valList.size > level) {
        if (level.isEven()) {
          if (valList[level] >= root.`val`) {
            return false
          }
        } else {
          if (valList[level] <= root.`val`) {
            return false
          }
        }
        valList[level] = root.`val`
      } else {
        valList.add(root.`val`)
      }

      return root.left?.run { isEvenOddTree(this, valList, level + 1) } ?: true
          && root.right?.run { isEvenOddTree(this, valList, level + 1) } ?: true
    }

    private fun Int.isEven() = this % 2 == 0
  }

  // Not Accepted
  object QUEUE : LeetCode1609EvenOddTree {

    override fun isEvenOddTree(root: TreeNode?): Boolean {
      if (root == null) return false

      val queue = ArrayDeque<TreeNode>()
      queue.add(root)
      var level = 0

      while (queue.isNotEmpty()) {
        val levelSize = queue.size
        var prevValue = if (level % 2 == 0) Int.MIN_VALUE else Int.MAX_VALUE

        for (i in 0 until levelSize) {
          val currentNode = queue.removeLast()
          val currentValue = currentNode.`val`

          // Check value parity and order constraints
          if (level % 2 == 0) {
            if (currentValue % 2 == 0 || currentValue <= prevValue) return false
          } else {
            if (currentValue % 2 != 0 || currentValue >= prevValue) return false
          }
          prevValue = currentValue

          // Add child nodes for the next level
          currentNode.left?.let { queue.add(it) }
          currentNode.right?.let { queue.add(it) }
        }
        level++
      }
      return true
    }
  }
}