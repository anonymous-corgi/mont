package algorithm.leetcode.p301to350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LeetCode310MinimumHeightTrees {
  
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> res = new ArrayList<>();
    if (n < 2 || edges == null || edges.length == 0) {
      res.add(0);
      return res;
    }
    int[] ins = new int[n];
    HashMap<Integer, Set<Integer>> neis = new HashMap<>();
    Queue<Integer> tasks = new LinkedList<>();
    //initiate
    for (int i = 0; i < n; i++) {
      neis.put(i, new HashSet<Integer>());
    }
    for (int i = 0; i < edges.length; i++) {
      ins[edges[i][0]]++;
      ins[edges[i][1]]++;
      neis.get(edges[i][0]).add(edges[i][1]);
      neis.get(edges[i][1]).add(edges[i][0]);
    }
    for (int i = 0; i < n; i++) {
      if (ins[i] == 1) {
        tasks.offer(i);
        ins[i] = 0;
      }
    }
    while (!tasks.isEmpty()) {
      res.clear();
      int size = tasks.size();
      for (int i = 0; i < size; i++) {
        int cur = tasks.poll();
        res.add(cur);
        for (Integer nei : neis.get(cur)) {
          if (ins[nei] > 1 && --ins[nei] == 1) {
            tasks.offer(nei);
          } 
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode310MinimumHeightTrees one 
    = new LeetCode310MinimumHeightTrees();
    int n = 6;
    int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
    one.findMinHeightTrees(n, edges);
  }

}
