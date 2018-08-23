package leetcode.p801to850;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import utils.Print;

public class LeetCode802FindEventualSafeStates {
  
  public static class BFS_method {
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
      int N = graph.length;
      int[] outs = new int[N];
      @SuppressWarnings("unchecked")
      Set<Integer>[] prev = new Set[N];
      Queue<Integer> tasks = new LinkedList<>();
      List<Integer> res = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        prev[i] = new HashSet<Integer>();
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < graph[i].length; j++) {
          prev[graph[i][j]].add(i);
          outs[i]++;
        }
      }
      for (int i = 0; i < N; i++) {
        if (outs[i] == 0) {
          tasks.offer(i);
        }
      }
      while (!tasks.isEmpty()) {
        Integer cursor = tasks.poll();
        res.add(cursor);
        for (int pre : prev[cursor]) {
          if (outs[pre]-- == 1) {
            tasks.offer(pre);
          }
        }
      }
      Collections.sort(res);
      return res;
    }
    
  }
  
  public static class DFS_method {
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
      boolean[] hasCycle = new boolean[graph.length];
      boolean[] globalVisited = new boolean[graph.length];
      boolean[] currentVisited = new boolean[graph.length];
      for (int i = 0; i < graph.length; i++) {
        hasCycle(graph, i, hasCycle, globalVisited, currentVisited);
      }
      List<Integer> res = new ArrayList<>();
      for (int i = 0; i < graph.length; i++) {
        if (!hasCycle[i]) res.add(i);
      }
      return res;
    }
    
    private boolean hasCycle(int[][] graph, int i, boolean[] hasCycle, 
                    boolean[] globalVisited, boolean[] currentVisited) {
      if (globalVisited[i]) return hasCycle[i];
      if (currentVisited[i]) return true;
      currentVisited[i] = true;
      for (int next : graph[i]) {
        if (hasCycle(graph, next, hasCycle, globalVisited, currentVisited)) {
          hasCycle[i] = true;
        }
      }
      globalVisited[i] = true;
      currentVisited[i] = false;
      return hasCycle[i];
    }
  
  }

  public static void main(String[] args) {
    LeetCode802FindEventualSafeStates.DFS_method one =
        new LeetCode802FindEventualSafeStates.DFS_method();
    int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
    Print.printList(one.eventualSafeNodes(graph));
  }

}
