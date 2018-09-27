package jiuzhang.c3.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import basicclass.TreeNode;
import utils.Print;

public class Traversal {  
  
  public static class InOrder {
    //In-order Inorder non-recursive
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      
      while (!stack.isEmpty() || root != null) {
        
        if (root != null) {
          stack.push(root);
          root = root.left;
        } else {
          res.add(stack.peek().val);
          root = stack.pop().right;
        }
        
      }
      
      return res;
    }
    
  }
  
  public static class PreOrder {
    //Pre-order Preorder non-recursive
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
  
  public static class PostOrder {
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

  public static void main(String[] args) {
    String data = "[1,2,3,4,5,6,7]";
    TreeNode root = TreeNode.deserializeSortedArray(data);
    Traversal.InOrder in = new Traversal.InOrder();
    Traversal.PreOrder pre = new Traversal.PreOrder();
    Traversal.PostOrder post = new Traversal.PostOrder();
    Print.printList(in.inorderTraversal(root));
    Print.printList(pre.preorderTraversal(root));
    Print.printList(post.postorderTraversal(root));
  }

}
