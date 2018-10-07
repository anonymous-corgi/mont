package leetcode.p751to800;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Test;

public class LeetCode787CheapestFlightsWithinKStops {

  interface Method {
    int findCheapestPrice(int n, int[][] flights, int src, int dst, int K);
  }

  public static class Dijkstra_method implements Method {

    static class Node implements Comparable<Node> {

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

    @Override
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
      int[] prices = new int[n];
      int[] stops = new int[n];
      @SuppressWarnings("unchecked")
      Map<Integer, Integer>[] next = new Map[n];
      for (int i = 0; i < n; i++) {
        next[i] = new HashMap<Integer, Integer>();
      }
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
        if (cursor.city == dst) {
          return cursor.cost;
        }
        if (cursor.stop > K) {
          continue;
        }
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

  public static class DP_method implements Method {

    @Override
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
      int[] prices = new int[n];
      int[] lastPrices = new int[n];

      Arrays.fill(prices, Integer.MAX_VALUE);
      prices[src] = 0;
      for (int stop = 0; stop <= K; stop++) {
        for (int i = 0; i < prices.length; i++) {
          lastPrices[i] = prices[i];
        }
        for (int[] flight : flights) {
          if (lastPrices[flight[0]] != Integer.MAX_VALUE) {
            prices[flight[1]] = Math.min(prices[flight[1]], lastPrices[flight[0]] + flight[2]);
          }
        }
      }
      return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }

  }

  private Method getMethod() {
    return new DP_method();
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    return getMethod().findCheapestPrice(n, flights, src, dst, K);
  }

  @Test
  public void testFindCheapestPrice1() {
    int n = 3;
    int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
    int src = 0;
    int dst = 2;
    int K = 1;
    int res = 200;
    assertEquals(res,findCheapestPrice(n, flights, src, dst, K));
  }

  @Test
  public void testFindCheapestPrice2() {
    int n = 3;
    int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
    int src = 0;
    int dst = 2;
    int K = 0;
    int res = 500;
    assertEquals(res,findCheapestPrice(n, flights, src, dst, K));
  }

  public static void main(String[] args) {
    int n = 3;
    int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
    int src = 0;
    int dst = 2;
    int K = 0;

    LeetCode787CheapestFlightsWithinKStops one =
        new LeetCode787CheapestFlightsWithinKStops();
    System.out.println(one.findCheapestPrice(n, flights, src, dst, K));
  }

}
