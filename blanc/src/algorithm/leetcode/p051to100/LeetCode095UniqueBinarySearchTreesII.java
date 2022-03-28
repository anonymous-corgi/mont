package algorithm.leetcode.p051to100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import algorithm.base.TreeNode;

public class LeetCode095UniqueBinarySearchTreesII {
  
  private static final int MOD = 100;
  private static Map<Integer, List<TreeNode>> cache = new HashMap<>();
  
  public LeetCode095UniqueBinarySearchTreesII() {
    if (cache.size() == 0) {
      List<TreeNode> zero = new ArrayList<>();
      zero.add(null);
      cache.put(0, zero);
    }
  }
  
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<TreeNode>();
    }
    return helper(1, n);
  }
  
  private List<TreeNode> helper(int start, int end) {
    if (start > end) {
      return cache.get(0);
    }
    int code = getCode(start, end);
    if (cache.containsKey(code)) {
      return cache.get(code);
    }
    List<TreeNode> res = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      List<TreeNode> ls = helper(start, i - 1);
      List<TreeNode> rs = helper(i + 1, end);
      for (TreeNode l : ls) {
        for (TreeNode r : rs) {
          TreeNode neo = new TreeNode(i);
          neo.left = l;
          neo.right = r;
          res.add(neo);
        }
      }
    }
    cache.put(code, res);
    return res;
  }
  
  private int getCode(int start, int end) {
    return start * MOD + end;
  }
  
  public static void main(String[] args) {
    LeetCode095UniqueBinarySearchTreesII one =
        new LeetCode095UniqueBinarySearchTreesII();
    int n = 3;
    List<TreeNode> res = one.generateTrees(n);
    for (int i = 0; i < res.size(); i++) {
      System.out.println(TreeNode.serialize(res.get(i)));
    }
  }
  
}
