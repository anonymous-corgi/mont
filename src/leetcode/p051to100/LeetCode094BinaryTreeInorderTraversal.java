package leetcode.p051to100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import basicclass.TreeNode;

public class LeetCode094BinaryTreeInorderTraversal {
  
  public static class Non_Recursive_method {
    //In-order Inorder non-recursive
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      
      while (!stack.isEmpty() || root != null) {
        
        if (root != null) {
          stack.push(root);
          root = root.left;
        } else {
          TreeNode cursor = stack.pop();
          res.add(cursor.val);
          root = cursor.right;
        }
        
      }
      
      return res;
    }
    
  }

}
