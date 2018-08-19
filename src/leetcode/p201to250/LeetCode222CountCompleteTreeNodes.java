package leetcode.p201to250;

import basicclass.TreeNode;

public class LeetCode222CountCompleteTreeNodes {
  
  public int height(TreeNode root) {
    return root == null ? -1 : 1 + height(root.left);
  }
  
  public int countNodes(TreeNode root) {
    int h = height(root);
    if (h < 0) {
      return 0;
    }
    //if height(root.right) == h - 1, that means the left subTree
    //is a complete tree, which has (2^h - 1) TreeNodes. (then plus root node) 
    //Otherwise, right subTree is a complete tree, 
    //which only has (2^(h - 1) - 1) TreeNodes
    if (height(root.right) == h - 1) {
      return (1 << h) + countNodes(root.right);
    } else {
      return (1 << h - 1) + countNodes(root.left);
    }
  }

  public static void main(String[] args) {
    String data = "[1,2,3,4,#,6]";
    TreeNode root = TreeNode.deserialize(data);
    LeetCode222CountCompleteTreeNodes one = 
        new LeetCode222CountCompleteTreeNodes();
    System.out.println(one.countNodes(root));
  }

}
