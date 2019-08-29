package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;

import basicclass.TreeNode;

@SuppressWarnings("unused")
public class LeetCode105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private interface Method {
        TreeNode buildTree(int[] preorder, int[] inorder);
    }

    private static class DFS implements Method {

        private Map<Integer, Integer> mInorderPosMap = new HashMap<>();

        @Override
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            mInorderPosMap.clear();
            for (int i = 0; i < inorder.length; i++) {
                mInorderPosMap.put(inorder[i], i);
            }
            return helper(preorder, 0, 0, inorder.length);
        }

        private TreeNode helper(int[] preorder, int pStart, int iStart, int len) {
            if (len == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[pStart]);
            if (len == 1) {
                return root;
            }
            int inorderPos = mInorderPosMap.get(preorder[pStart]);
            int lLen = inorderPos - iStart;
            int rLen = len - lLen - 1;

            root.left = helper(preorder, pStart + 1, iStart, lLen);
            root.right = helper(preorder, pStart + 1 + lLen, iStart + lLen + 1, rLen);
            return root;
        }
    }
}
