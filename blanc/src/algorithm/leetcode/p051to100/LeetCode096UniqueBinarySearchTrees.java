package algorithm.leetcode.p051to100;

import java.util.ArrayList;
import java.util.List;

public class LeetCode096UniqueBinarySearchTrees {
  
  private static List<Integer> cache = new ArrayList<>();
  
  public LeetCode096UniqueBinarySearchTrees() {
    if (cache.size() == 0) {
      cache.add(1);
      cache.add(1);
      cache.add(2);
    }
  }
  
  public int numTrees(int n) {
    if (cache.size() > n) {
      return cache.get(n);
    }
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += numTrees(i - 1) * numTrees(n - i);
    }
    cache.add(sum);
    return sum;
  }

  public static void main(String[] args) {
    LeetCode096UniqueBinarySearchTrees one = 
        new LeetCode096UniqueBinarySearchTrees();
    int n = 3;
    System.out.print(one.numTrees(n));
  }

}
