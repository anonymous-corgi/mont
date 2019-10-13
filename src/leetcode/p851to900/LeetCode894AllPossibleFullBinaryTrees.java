package leetcode.p851to900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import algorithm.base.TreeNode;

public class LeetCode894AllPossibleFullBinaryTrees {
  
  public LeetCode894AllPossibleFullBinaryTrees() {
    if (cache.size() == 0) {
      cache.put(1, new ArrayList<TreeNode>(Arrays.asList(new TreeNode(0))));
    }
  }
  
  private static Map<Integer, List<TreeNode>> cache = new HashMap<>();
  public List<TreeNode> allPossibleFBT(int N) {
    if (N % 2 != 1) {
      return new ArrayList<TreeNode>();
    }
    return helper(N);
  }
  
  private List<TreeNode> helper(int n) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }
    List<TreeNode> res = new ArrayList<>();
    for (int i = 1; i < n; i += 2) {
      List<TreeNode> left = helper(i);
      List<TreeNode> right = helper(n - i - 1);
      for (TreeNode l : left) {
        for (TreeNode r : right) {
          TreeNode root = new TreeNode(0);
          root.left = l;
          root.right = r;
          res.add(root);
        }
      }
    }
    cache.put(n, res);
    return res;
  }

  public static void main(String[] args) {

  }

}
