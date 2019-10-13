package algorithm.leetcode.p851to900;

import java.util.HashSet;

public class LeetCode886PossibleBipartition {
  
  public boolean possibleBipartition(int N, int[][] dislikes) {
    @SuppressWarnings("unchecked")
    HashSet<Integer>[] set = new HashSet[N];
    for (int i = 0; i < N; i++) {
      set[i] = new HashSet<Integer>();
    }
    for (int[] each : dislikes) {
      set[each[0] - 1].add(each[1] - 1);
      set[each[1] - 1].add(each[0] - 1);
    }
    int[] group = new int[N];
    for (int i = 0; i < N; i++) {
      if (group[i] != 0) {
        continue;
      }
      group[i] = 1;
      if (dfs(i, group, set)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean dfs(int i,int[] group, HashSet<Integer>[] set) {
    for (Integer each : set[i]) {
      if (group[each] != 0) {
        if (group[each] == group[i]) {
          return true;
        }
      } else {
        group[each] = -group[i];
        if (dfs(each, group, set)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    LeetCode886PossibleBipartition one =
        new LeetCode886PossibleBipartition();
    int N = 4;
    int[][] dislikes = {{1,2},{1,3},{2,4},{2,3}};
    System.out.println(one.possibleBipartition(N, dislikes));
  }

}
