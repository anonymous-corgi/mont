package leetcode.p651to700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import algorithm.base.TreeNode;

public class LeetCode652FindDuplicateSubtrees {
  
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    List<TreeNode> res = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    helper(root, map, res);
    return res;
  }
  
  private String helper(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
    if (root == null) {
      return "#";
    }
    String str = "" + root.val + "/" + helper(root.left, map, res) 
                    + "/" + helper(root.right, map, res);
    if (map.getOrDefault(str, 0) == 1) {
      res.add(root);
    }
    map.put(str, map.getOrDefault(str, 0) + 1);
    return str;
  }

  public static void main(String[] args) {

  }

}
