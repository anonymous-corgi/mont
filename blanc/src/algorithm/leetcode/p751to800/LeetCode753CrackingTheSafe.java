package algorithm.leetcode.p751to800;

import java.util.HashSet;
import java.util.Set;

public class LeetCode753CrackingTheSafe {
  
  private int MAX = 1;
  private int TOTAL;
  public String crackSafe(int n, int k) {
    StringBuilder sb = new StringBuilder();
    Set<Integer> visited = new HashSet<>(TOTAL * 2);
    TOTAL = (int) Math.pow(k, n);
    for (int i = 0; i < n; i++) {
      MAX *= 10;
      sb.append('0');
    }
    visited.add(0);
    dfs(sb, 0, k, visited);
    return sb.toString();
  }
  
  private boolean dfs(StringBuilder sb, int value, int k, Set<Integer> visited) {
    if (visited.size() == TOTAL) {
      return true;
    }

    for (int i = 0; i < k; i++) {
      int neoValue = (value * 10 + i) % MAX;
      if (!visited.contains(neoValue)) {
        sb.append(i);
        visited.add(neoValue);
        if (dfs(sb, neoValue, k, visited)) {
          return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        visited.remove(neoValue);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    LeetCode753CrackingTheSafe one = 
        new LeetCode753CrackingTheSafe();
    int n = 1;
    int k = 2;
    System.out.println(one.crackSafe(n, k));
  }

}
