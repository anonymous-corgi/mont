package leetcode.p251to300;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeetCode261GraphValidTree {

    private interface Method {
        boolean validTree(int n, int[][] edges);
    }

    private static class BFS implements Method {

        @Override
        public boolean validTree(int n, int[][] edges) {
            int count = 0;
            boolean[] visited = new boolean[n];
            List<Integer>[] neighbors = new List[n];
            Queue<Integer> taskQueue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                neighbors[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                neighbors[edge[0]].add(edge[1]);
                neighbors[edge[1]].add(edge[0]);
            }

            visited[0] = true;
            taskQueue.offer(0);
            while (!taskQueue.isEmpty()) {
                count++;
                int cursor = taskQueue.poll();
                for (int neighbor : neighbors[cursor]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        taskQueue.offer(neighbor);
                    }
                }
            }
            return count == n;
        }
    }
}
