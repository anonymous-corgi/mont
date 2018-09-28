package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;
import basicclass.TreeNode;

public class LeetCode105ConstructBinaryTreeFromPreorderAndInorderTraversal {
  
  private Map<Integer, Integer> inorderPosMap;
  
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    inorderPosMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderPosMap.put(inorder[i], i);
    }
    return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
  }
  
  private TreeNode helper(int[] preorder, int[] inorder, 
                          int pStart, int pEnd, 
                          int iStart, int iEnd) {
    if (iStart > iEnd) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[pStart]);
    if (iStart == iEnd) {
      return root;
    }
    int inorderPos = inorderPosMap.get(preorder[pStart]);
    int leftTreeLen = inorderPos - iStart - 1;
    
    root.left = helper(preorder, inorder, pStart + 1, pStart + 1 + leftTreeLen, iStart, inorderPos - 1);
    root.right = helper(preorder, inorder, pStart + 2 + leftTreeLen, pEnd, inorderPos + 1, iEnd);
    return root;
  }
  
}
