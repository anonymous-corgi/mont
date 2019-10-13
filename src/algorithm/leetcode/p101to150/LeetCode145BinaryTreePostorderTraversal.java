package algorithm.leetcode.p101to150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import algorithm.base.TreeNode;

public class LeetCode145BinaryTreePostorderTraversal {
  
  public static class Reverse_Preorder_method {
    
    public List<Integer> postorderTraversal(TreeNode root) {
      LinkedList<Integer> res = new LinkedList<>();
      Stack<TreeNode> stack = new Stack<>();
      while (!stack.isEmpty() || root != null) {
        
        if (root != null) {
          res.addFirst(root.val); 
          stack.push(root);
          root = root.right;
        } else {
          root = stack.pop().left;
        }
        
      }
      
      return res;
    }
    
  }
  
  public static class Regular_method {
    
    //Post-order Postorder non-recursive
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      TreeNode prev = null;
      while (!stack.isEmpty() || root != null) {
        
        if (root != null) {
          stack.push(root);
          root = root.left;
        } else {
          if (stack.peek().right != null && stack.peek().right != prev) {
            root = stack.peek().right;
          } else {
            res.add(stack.peek().val);
            prev = stack.pop();
          }
        }
        
      }
      
      return res;
    }
    
  }
  

}
