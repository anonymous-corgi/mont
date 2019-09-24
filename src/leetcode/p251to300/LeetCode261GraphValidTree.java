package leetcode.p251to300;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 261. Graph Valid Tree
 * Medium
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 */
@RunWith(Parameterized.class)
public class LeetCode261GraphValidTree {
    private final int n;
    private final int[][] edges;
    private final boolean expected;

    public LeetCode261GraphValidTree(int n, int[][] edges, boolean expected) {
        this.n = n;
        this.edges = edges;
        this.expected = expected;
    }

    private interface Method {
        boolean validTree(int n, int[][] edges);
    }

    private static final class BFS implements Method {

        public boolean validTree(int n, int[][] edges) {
            int count = 0;
            int[] visitedFrom = new int[n];
            List<Integer>[] neighbors = new List[n];
            Queue<Integer> taskQueue = new ArrayDeque<>();
            Arrays.fill(visitedFrom, -1);
            for (int i = 0; i < n; i++) {
                neighbors[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                neighbors[edge[0]].add(edge[1]);
                neighbors[edge[1]].add(edge[0]);
            }

            visitedFrom[0] = 0;
            taskQueue.offer(0);
            while (!taskQueue.isEmpty()) {
                count++;
                int cursor = taskQueue.poll();
                for (int neighbor : neighbors[cursor]) {
                    if (visitedFrom[neighbor] == -1) {
                        visitedFrom[neighbor] = cursor;
                        taskQueue.offer(neighbor);
                    } else if (visitedFrom[cursor] != neighbor) {
                        return false;
                    }
                }
            }
            return count == n;
        }
    }

    private static Method getMethod() {
        return new BFS();
    }

    private void test(int n, int[][] edges, boolean expected) {
        boolean actual = getMethod().validTree(n, edges);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test(n, edges, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}, false},
                {5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}, true},
        };
    }
}
