package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;
import basicclass.TreeNode;

public class LeetCode106ConstructBinaryTreeFromInorderAndPostorderTraversal {
  
  private Map<Integer, Integer> inorderPosMap;
  
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    inorderPosMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderPosMap.put(inorder[i], i);
    }
    return helper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
  }
  
  private TreeNode helper(int[] inorder, int[] postorder, 
                          int iStart, int iEnd, 
                          int pStart, int pEnd) {
    if (iStart > iEnd) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[pEnd]);
    if (iStart == iEnd) {
      return root;
    }
    int inorderPos = inorderPosMap.get(postorder[pEnd]);
    int leftTreeLen = inorderPos - iStart - 1;
    
    root.left = helper(inorder, postorder, iStart, iStart + leftTreeLen, pStart, pStart + leftTreeLen);
    root.right = helper(inorder, postorder, iStart + leftTreeLen + 2, iEnd, pStart + leftTreeLen + 1, pEnd - 1);
    return root;
  }
  
}
