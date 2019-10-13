package algorithm.leetcode.p101to150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import algorithm.base.TreeNode;

public class LeetCode144BinaryTreePreorderTraversal {
  
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    
    while (!stack.isEmpty() || root != null) {
      
      if (root != null) {
        res.add(root.val);
        stack.push(root);
        root = root.left;
      } else {
        root = stack.pop().right;
      }
      
    }
    
    return res;
  }

}
