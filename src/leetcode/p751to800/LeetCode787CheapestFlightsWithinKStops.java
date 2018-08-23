package leetcode.p751to800;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode787CheapestFlightsWithinKStops {
  
  public static class Dijkstra_method {
    
    static class Node implements Comparable<Node>{
      int city;
      int cost;
      int stop;
      
      public Node(int city, int cost, int stop) {
        this.city = city;
        this.cost = cost;
        this.stop = stop;
      }
      
      @Override
      public int compareTo(Node node) {
        return this.cost - node.cost;
      }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
      int[] prices = new int[n];
      int[] stops = new int[n];
      @SuppressWarnings("unchecked")
      Map<Integer, Integer>[] next = new Map[n];
      for (int i = 0; i < n; i++) { next[i] = new HashMap<Integer, Integer>(); }
      Queue<Node> tasks = new PriorityQueue<>();
      
      Arrays.fill(prices, Integer.MAX_VALUE);
      Arrays.fill(stops, Integer.MAX_VALUE);
      prices[src] = 0;
      stops[src] = 0;
      for (int i = 0; i < flights.length; i++) {
        next[flights[i][0]].put(flights[i][1], flights[i][2]);
      }
      tasks.offer(new Node(src, 0, 0));
      while (!tasks.isEmpty()) {
        Node cursor = tasks.poll();
        if (cursor.city == dst) { return cursor.cost; }
        if (cursor.stop > K) { continue; }
        for (Map.Entry<Integer, Integer> nxt : next[cursor.city].entrySet()) {
          int nextCity = nxt.getKey();
          int price = cursor.cost + nxt.getValue();
          if (price < prices[nextCity]) {
            prices[nextCity] = price;
            tasks.offer(new Node(nextCity, price, cursor.stop + 1));
          } else if (cursor.stop + 1 < stops[nextCity]) {
            stops[nextCity] = cursor.stop + 1;
            tasks.offer(new Node(nextCity, price, cursor.stop + 1));
          }
        }
      }
      return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }
    
  }
  
  public static class DP_method {
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
      int[] prices = new int[n];
      int[] lastPrices = new int[n];
      int[][] next = new int[n][n];
      
      Arrays.fill(prices, Integer.MAX_VALUE);
      Arrays.fill(lastPrices, Integer.MAX_VALUE);
      prices[src] = 0;
      lastPrices[src] = 0;
      for (int i = 0; i < flights.length; i++) {
        next[flights[i][0]][flights[i][1]] = flights[i][2];
      }
      for (int stop = 0; stop <= K; stop++) {
        int[] temp = prices;
        prices = lastPrices;
        lastPrices = temp;
        for (int city = 0; city < n; city++) {            
          if (lastPrices[city] == Integer.MAX_VALUE) { continue; }
          for (int nextCity = 0; nextCity < n; nextCity++) {
            if (next[city][nextCity] != 0) {
              prices[nextCity] = Math.min(prices[nextCity], 
                                  lastPrices[city] + next[city][nextCity]);
            }
          }
        }
      }
      return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }
    
  }

  public static void main(String[] args) {
//    //Result: 200
//    int n = 3;
//    int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
//    int src = 0;
//    int dst = 2;
//    int K = 1;
    
    int n = 3;
    int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
    int src = 0;
    int dst = 2;
    int K = 0;
//    LeetCode787CheapestFlightsWithinKStops.Dijkstra_method one =
//        new LeetCode787CheapestFlightsWithinKStops.Dijkstra_method();
    LeetCode787CheapestFlightsWithinKStops.DP_method one =
        new LeetCode787CheapestFlightsWithinKStops.DP_method();
    System.out.println(one.findCheapestPrice(n, flights, src, dst, K));
  }

}
