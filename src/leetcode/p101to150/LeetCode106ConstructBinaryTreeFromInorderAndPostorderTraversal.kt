package leetcode.p101to150

import java.util.HashMap
import basicclass.TreeNode

class LeetCode106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    private var mInorderPosMap: MutableMap<Int, Int> = mutableMapOf()

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        mInorderPosMap = HashMap()
        for (i in inorder.indices) {
            mInorderPosMap[inorder[i]] = i
        }
        return helper(postorder, 0, postorder.size - 1, 0, inorder.size - 1)
    }

    private fun helper(postorder: IntArray,
                       pStart: Int, pEnd: Int,
                       iStart: Int, iEnd: Int): TreeNode? {
        if (iStart > iEnd) {
            return null
        }
        val root = TreeNode(postorder[pEnd])
        if (iStart == iEnd) {
            return root
        }
        val iPos = mInorderPosMap[postorder[pEnd]]!!
        val rightTreeLen = iEnd - iPos

        root.left = helper(postorder, pStart, pEnd - 1 - rightTreeLen, iStart, iPos - 1)
        root.right = helper(postorder, pEnd - rightTreeLen, pEnd - 1, iPos + 1, iEnd)
        return root
    }
}
