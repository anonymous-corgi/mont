package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;

import algorithm.base.TreeNode;

@SuppressWarnings("unused")
public class LeetCode106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    private interface Method {
        TreeNode buildTree(int[] inorder, int[] postorder);
    }

    private static class DFS implements Method {

        private Map<Integer, Integer> mInorderPosMap = new HashMap<>();

        @Override
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            mInorderPosMap.clear();
            for (int i = 0; i < inorder.length; i++) {
                mInorderPosMap.put(inorder[i], i);
            }
            return helper(postorder, 0, 0, postorder.length);
        }

        private TreeNode helper(int[] postorder, int iStart, int pStart, int len) {
            if (len == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[pStart + len - 1]);
            if (len == 1) {
                return root;
            }
            int inorderPos = mInorderPosMap.get(postorder[pStart + len - 1]);
            int lLen = inorderPos - iStart;
            int rLen = len - lLen - 1;

            root.left = helper(postorder, iStart, pStart, lLen);
            root.right = helper(postorder, iStart + lLen + 1, pStart + lLen, rLen);
            return root;
        }
    }
}
