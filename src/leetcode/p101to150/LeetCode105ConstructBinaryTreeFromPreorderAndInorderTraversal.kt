package leetcode.p101to150

import basicclass.TreeNode

class LeetCode105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private var mInorderPosMap: MutableMap<Int, Int> = mutableMapOf()

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        mInorderPosMap.clear()
        for (i in inorder.indices) {
            mInorderPosMap[inorder[i]] = i
        }
        return helper(preorder, 0, preorder.size - 1, 0, inorder.size - 1)
    }

    private fun helper(preorder: IntArray,
                       pStart: Int, pEnd: Int,
                       iStart: Int, iEnd: Int): TreeNode? {
        if (iStart > iEnd) {
            return null
        }
        val root = TreeNode(preorder[pStart])
        if (iStart == iEnd) {
            return root
        }
        val iPos = mInorderPosMap[preorder[pStart]]!!
        val leftTreeLen = iPos - iStart

        root.left = helper(preorder, pStart + 1, pStart + leftTreeLen, iStart, iPos - 1)
        root.right = helper(preorder, pStart + 1 + leftTreeLen, pEnd, iPos + 1, iEnd)
        return root
    }
}
