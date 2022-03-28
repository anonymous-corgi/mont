package algorithm.leetcode.p851to900;

import java.util.HashMap;
import java.util.Map;
import algorithm.base.TreeNode;

public class LeetCode889ConstructBinaryTreeFromPreorderAndPostorderTraversal {
  
  private Map<Integer, Integer> postorderPosMap;
  
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    postorderPosMap = new HashMap<>();
    for (int i = 0; i < post.length; i++) {
      postorderPosMap.put(post[i], i);
    }
    return helper(pre, post, 0, pre.length - 1, 0, pre.length - 1);
  }
  
  private TreeNode helper(int[] pre, int[] post, 
                          int preStart, int preEnd, 
                          int posStart, int posEnd) {
    if (preStart > preEnd) {
      return null;
    }
    TreeNode root = new TreeNode(pre[preStart]);
    if (preStart == preEnd) {
      return root;
    }
    preStart++;
    posEnd--;
    int postorderPos = postorderPosMap.get(pre[preStart]);
    int leftTreeLen = postorderPos - posStart;
    
    root.left = helper(pre, post, preStart, preStart + leftTreeLen, posStart, postorderPos);
    root.right = helper(pre, post, preStart + leftTreeLen + 1, preEnd, postorderPos + 1, posEnd);
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
