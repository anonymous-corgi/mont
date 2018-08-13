package leetcode.p801to850;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LeetCode815BusRoutes {
  
//  DP method:
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
  
// BFS method:
  public int numBusesToDestination_BFS(int[][] routes, int S, int T) {
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
  
  
//  This is another BFS version - TEL
//  public int numBusesToDestination(int[][] routes, int S, int T) {
//    if (S == T) {
//      return 0;
//    }
//    int step = 0;
//    Map<Integer, Set<Integer>> map = buildMap(routes);
//    HashSet<Integer> visited = new HashSet<>();
//    Queue<Integer> tasks = new LinkedList<>();
//    tasks.offer(S);
//    visited.add(S);
//    
//    while (!tasks.isEmpty()) {
//      step++;
//      int size = tasks.size();
//      for (int i = 0; i < size; i++) {
//        Integer cursor = tasks.poll();
//        for (Integer nei : map.get(cursor)) {
//          if (nei == T) {
//            return step;
//          }
//          if (!visited.contains(nei)) {
//            tasks.offer(nei);
//            visited.add(nei);
//          }
//        }
//      }
//    }
//    return -1;
//  }
//  
//  private Map<Integer, Set<Integer>> buildMap(int[][] routes) {
//      Map<Integer, Set<Integer>> map = new HashMap<>();
//      for (int i = 0; i < routes.length; i++) {
//          for (int j = 0; j < routes[i].length; j++) {
//              for (int k = 0; k < routes[i].length; k++) {
//                  if (j != k) {
//                      Set<Integer> set = map.getOrDefault(routes[i][j], new HashSet<Integer>());
//                      set.add(routes[i][k]);
//                      map.put(routes[i][j], set);
//                  }
//              }
//          }
//      }
//      return map;
//  }

  public static void main(String[] args) {
    LeetCode815BusRoutes one = 
        new LeetCode815BusRoutes();
    int[][] routes = {{1, 2, 7}, {3, 6, 7}};
    int S = 1;
    int T = 6;
    System.out.println(one.numBusesToDestination(routes, S, T));
  }

}
