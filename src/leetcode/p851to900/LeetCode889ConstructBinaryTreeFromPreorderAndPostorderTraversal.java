package leetcode.p851to900;

import basicclass.TreeNode;

public class LeetCode889ConstructBinaryTreeFromPreorderAndPostorderTraversal {
  
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    if (pre == null || pre.length == 0) {
      return null;
    }
    return helper(pre, post, 0, pre.length - 1, 0, pre.length - 1);
  }
  
  private TreeNode helper(int[] pre, int[] post, 
                          int preS, int preE, int posS, int posE) {
    if (preS > preE) {
      return null;
    }
    TreeNode root = new TreeNode(pre[preS]);
    if (preS == preE) {
      return root;
    }
    preS++;
    posE--;
    int leftTreeLen = 0;
    for (int i = posE; i >= posS; i--) {
      if (post[i] == pre[preS]) {
        leftTreeLen = i - posS;
        break;
      }
    }
    
//    int leftTreeLen = 0;
//    long flag = 0;
//    for (int len = preE - preS; leftTreeLen < len; leftTreeLen++) {
//      flag ^= (1 << pre[preS + leftTreeLen]);
//      flag ^= (1 << post[posS + leftTreeLen]);
//      if (flag == 0) {
//        break;
//      }
//    }
    
    root.left = helper(pre, post, preS, preS + leftTreeLen, posS, posS + leftTreeLen);
    root.right = helper(pre, post, preS + leftTreeLen + 1, preE, posS + leftTreeLen + 1, posE);
    return root;
  }

  public static void main(String[] args) {
    LeetCode889ConstructBinaryTreeFromPreorderAndPostorderTraversal one =
        new LeetCode889ConstructBinaryTreeFromPreorderAndPostorderTraversal();
////  Expect: [7,2,null,6,null,1,null,5,null,4,null,3]
//    int[] pre = {7,2,6,1,5,4,3};
//    int[] post = {3,4,5,1,6,2,7};
    
//  Expect: [1,4,null,3,null,5,null,2]
    int[] pre = {1,4,3,5,2};
    int[] post ={2,5,3,4,1};
    TreeNode root = one.constructFromPrePost(pre, post);
    System.out.println(TreeNode.serialize(root));
  }

}