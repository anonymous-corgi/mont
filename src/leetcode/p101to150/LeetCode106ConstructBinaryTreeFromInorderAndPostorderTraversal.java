package leetcode.p101to150;

import java.util.HashMap;
import java.util.Map;
import basicclass.TreeNode;

public class LeetCode106ConstructBinaryTreeFromInorderAndPostorderTraversal {
  
  private Map<Integer, Integer> map = new HashMap<>();
  
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return helper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
  }
  
  private TreeNode helper(int[] in, int[] post, 
                          int inStart, int inEnd, 
                          int poStart, int poEnd) {
    if (inStart > inEnd) {
      return null;
    }
    TreeNode root = new TreeNode(post[poEnd]);
    if (inStart == inEnd) {
      return root;
    }
    int leftTree = map.get(post[poEnd]) - inStart - 1;
    root.left = helper(in, post, inStart, inStart + leftTree, poStart, poStart + leftTree);
    root.right =  helper(in, post, inStart + leftTree + 2, inEnd, poStart + leftTree + 1, poEnd - 1);
    return root;
  }
  
  public static void main(String[] args) {
    
  }
  
}
