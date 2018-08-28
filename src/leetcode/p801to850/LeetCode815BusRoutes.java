package leetcode.p801to850;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import utils.Print;

public class LeetCode815BusRoutes {
  
//  DP method:
  public static class DP_method {
    
    public int numBusesToDestination(int[][] routes, int S, int T) {
      if (S == T) { return 0; }
      int maxStop = 0;
      for (int[] route : routes) {
        for (int stop : route) {
          maxStop = Math.max(maxStop, stop);
        }
      }
      int lineNum = routes.length;
      //ans[i] records the minimum transportation times 
      //from i-th stop to S;
      int[] ans = new int[maxStop + 1];
      Arrays.fill(ans, lineNum + 1);
      ans[S] = 0;
      for (int i = 0; i < lineNum; i++) {
        for (int[] route : routes) {
          int min = lineNum + 1;
          //Find the minimum ans[i] in a bus-line (route)
          //Because all stops (except i-th stop) in a bus-line is 
          // 1 transportation time away from the i-th stop.
          // ans[j] = min{ans[j], ans[i] + 1}, i, j in the same route.
          for (int stop : route) { min = Math.min(min, ans[stop]); }
          min += 1;
          for (int stop : route) { ans[stop] = Math.min(min, ans[stop]); }
        }
        if (ans[T] != lineNum + 1) { break; }
      }
      return ans[T] == lineNum + 1 ? -1 : ans[T];
    }
    
  }
  
// BFS method:
  public static class BFS_method {
    
    public int numBusesToDestination(int[][] routes, int S, int T) {
      if (S == T) {
        return 0;
      }
      int step = 0;
      Map<Integer, Set<Integer>> busLines = new HashMap<>();
      HashSet<Integer> visited = new HashSet<>();
      Queue<Integer> tasks = new LinkedList<>();
      for (int i = 0; i < routes.length; i++) {
        for (int j = 0; j < routes[i].length; j++) {
          Set<Integer> lines = busLines.getOrDefault(routes[i][j], new HashSet<Integer>());
          lines.add(i);
          busLines.put(routes[i][j], lines);
        }
      }
      tasks.offer(S);
      visited.add(S);
      while (!tasks.isEmpty()) {
        step++;
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
          Integer cursor = tasks.poll();
          for (Integer line : busLines.get(cursor)) {
            for (int j = 0; j < routes[line].length; j++) {
              if (routes[line][j] == T) {
                return step;
              }
              if (!visited.contains(routes[line][j])) {
                tasks.offer(routes[line][j]);
                visited.add(routes[line][j]);
              }
            }
          }
        }
      }
      return -1;
    }
    
  }
  
  
  public static class DFS_method {
    
    private int T;
    private Map<Integer, List<Integer>> stopFindLines;
    
    public List<List<Integer>> findAllBusesToDestination(int[][] routes, int S, int T) {
      List<List<Integer>> res = new ArrayList<>();
      stopFindLines = new HashMap<>();
      if (S == T) {
        return res;
      }
      this.T = T;
      for (int i = 0; i < routes.length; i++) {
        for (int j = 0; j < routes[i].length; j++) {
          List<Integer> lines = stopFindLines.getOrDefault(routes[i][j], new ArrayList<Integer>());
          lines.add(i);
          stopFindLines.put(routes[i][j], lines);
        }
      }
      dfs(routes, new boolean[routes.length], S, new ArrayList<Integer>(), res);
      return res;
    }
    
    private void dfs(int[][] routes, boolean[] visited, int stop, List<Integer> sub, List<List<Integer>> res) {
      if (stop == T) {
        res.add(new ArrayList<>(sub));
        return;
      }
      
      List<Integer> nextLines = stopFindLines.get(stop);
      for (int nextLine : nextLines) {
        if (!visited[nextLine]) {
          visited[nextLine] = true;
          for (int nextStop : routes[nextLine]) {
            if (nextStop != stop) {
              sub.add(nextLine);
              dfs(routes, visited, nextStop, sub, res);
              sub.remove(sub.size() - 1);
            }
          }
          visited[nextLine] = false;
        }
      }
    }
    
  }

  public static void main(String[] args) {
    LeetCode815BusRoutes.DFS_method one = 
        new LeetCode815BusRoutes.DFS_method();
    int[][] routes = {{1,2,3,4}, {3,5,6,7}, {7,8,9,11}, {2,9,10}};
    int S = 1;
    int T = 10;
    List<List<Integer>> res = one.findAllBusesToDestination(routes, S, T);
    Print.printListList(res);
//    System.out.println(one.numBusesToDestination(routes, S, T));
  }

}
