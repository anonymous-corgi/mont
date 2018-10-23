package interview.google;

import basicclass.TreeNode;

public class ValidateCompleteTree {

  private boolean isValid;
  
  public boolean isCompleteTree(TreeNode root) {
    isValid = true;
    helper(root);
    return isValid;
  }
  
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int lh = helper(root.left);
    int rh = helper(root.right);
    if (rh > lh) {
      isValid = false;
    }
    return Math.min(lh, rh) + 1;
  }

  public static void main(String[] args) {
    ValidateCompleteTree one = 
        new ValidateCompleteTree();
    String data = "[1,2,3,4,#,5]";
    TreeNode root = TreeNode.deserialize(data);
    System.out.println(one.isCompleteTree(root));
  }

}
