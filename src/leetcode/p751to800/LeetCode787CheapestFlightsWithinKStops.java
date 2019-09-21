package leetcode.p751to800;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode787CheapestFlightsWithinKStops {
    private final int n;
    private final int[][] flights;
    private final int src;
    private final int dst;
    private final int K;
    private final int expected;

    public LeetCode787CheapestFlightsWithinKStops(int n, int[][] flights, int src, int dst, int k, int expected) {
        this.n = n;
        this.flights = flights;
        this.src = src;
        this.dst = dst;
        this.K = k;
        this.expected = expected;
    }

    private interface Method {
        int findCheapestPrice(int n, int[][] flights, int src, int dst, int K);
    }

    private static final class DP implements Method {

        @Override
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            int[] curCost = new int[n];
            int[] preCost = new int[n];
            Arrays.fill(curCost, Integer.MAX_VALUE);
            curCost[src] = 0;
            for (int stop = 0; stop <= K; stop++) {
                System.arraycopy(curCost, 0, preCost, 0, curCost.length);
                for (int[] flight : flights) {
                    int from = flight[0];
                    int to = flight[1];
                    int cost = flight[2];
                    if (preCost[from] != Integer.MAX_VALUE) {
                        curCost[to] = Math.min(curCost[to], preCost[from] + cost);
                    }
                }
            }
            return curCost[dst] != Integer.MAX_VALUE ? curCost[dst] : -1;
        }
    }

    private static final class Dijkstra implements Method {

        private static class Node implements Comparable<Node> {
            int city;
            int stop;
            int cost;

            private Node(int city, int stop, int cost) {
                this.city = city;
                this.stop = stop;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node o) {
                return this.cost - o.cost;
            }
        }

        @Override
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            //noinspection unchecked
            List<int[]>[] srcToLines = new List[n];
            // The min cost of getting to a city.
            int[] minCost = new int[n];
            // The min stop of getting to a city.
            int[] minStop = new int[n];
            Queue<Node> taskQueue = new PriorityQueue<>();

            Arrays.fill(minCost, 0, src, Integer.MAX_VALUE);
            Arrays.fill(minCost, src + 1, minCost.length, Integer.MAX_VALUE);
            Arrays.fill(minStop, 0, src, Integer.MAX_VALUE);
            Arrays.fill(minStop, src + 1, minStop.length, Integer.MAX_VALUE);
            for (int[] flight : flights) {
                if (srcToLines[flight[0]] == null) {
                    srcToLines[flight[0]] = new ArrayList<>();
                }
                srcToLines[flight[0]].add(flight);
            }

            taskQueue.offer(new Node(src, 0, 0));
            while (!taskQueue.isEmpty()) {
                Node cursor = taskQueue.poll();
                if (cursor.city == dst) {
                    return cursor.cost;
                }
                if (cursor.stop > K || srcToLines[cursor.city] == null) {
                    continue;
                }
                for (int[] line : srcToLines[cursor.city]) {
                    int nextCity = line[1];
                    int cost = cursor.cost + line[2];
                    if (cost < minCost[nextCity]) {
                        minCost[nextCity] = cost;
                        taskQueue.offer(new Node(nextCity, cursor.stop + 1, cost));
                    } else if (cursor.stop + 1 < minStop[nextCity]) {
                        minStop[nextCity] = cursor.stop;
                        taskQueue.offer(new Node(nextCity, cursor.stop + 1, cost));
                    }
                }
            }
            return -1;
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    private void test(int n, int[][] flights, int src, int dst, int K, int expected) {
        int actual = getMethod().findCheapestPrice(n, flights, src, dst, K);
        assertThat(actual, is(expected));
    }

    @Test
    public void test() {
        test(n, flights, src, dst, K, expected);
    }

    @Parameterized.Parameters
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1, 200},
                {3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0, 500},};
    }
}
