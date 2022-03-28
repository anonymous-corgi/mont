package algorithm.leetcode.p751to800;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCode778SwimInRisingWater {
  
  
  //BFS method:
  private static class Node {
    int x, y, h;
    public Node(int x, int y, int h) {
      this.x = x;
      this.y = y;
      this.h = h;
    }
  }
  
  public int swimInWater_BFS(int[][] grid) {
    if (grid == null || grid.length == 0 
        || grid[0] == null || grid[0].length == 0) {
      return 0;
    }
    final int n = grid.length;
    final int last = n - 1;
    final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int res = 0;
    boolean[][] visited = new boolean[n][n];
    PriorityQueue<Node> taskList = new PriorityQueue<Node>((a, b) -> (a.h - b.h));
    taskList.offer(new Node(0, 0, grid[0][0]));
    visited[0][0] = true;
    while (!taskList.isEmpty()) {
      Node cur = taskList.poll();
      res = Math.max(res, cur.h);
      if (cur.x == last && cur.y == last) {
        return res;
      }
      for (int[] dir : DIRS) {
        int nx = cur.x + dir[0];
        int ny = cur.y + dir[1];
        if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
          continue;
        }
        taskList.offer(new Node(nx, ny, grid[nx][ny]));
        visited[nx][ny] = true;
      }
    }
    return res;
  }
  
  //DFS method:
  public int swimInWater_DFS(int[][] grid) {
    if (grid == null || grid.length == 0 
        || grid[0] == null || grid[0].length == 0) {
      return 0;
    }
    int n = grid.length;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dfs(grid, dp, 0, 0, grid[0][0]);
    return dp[n - 1][n - 1];
  }
  
  private void dfs(int[][] grid, int[][] dp, int x, int y, int height) {
    int n = grid.length;
    if (x < 0 || x >= n || y < 0 || y >= n) {
      return;
    }
    //height >= dp[x][y] eliminates a worse possibility
    //dp[x][y] == grid[x][y] mean this node has been visited
    //and the current result is optimal. 
    if (height >= dp[x][y] || dp[x][y] == grid[x][y]) {
      return;
    }
    dp[x][y] = Math.max(height, grid[x][y]);
    final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    for (int[] dir : DIRS) {
      dfs(grid, dp, x + dir[0], y + dir[1], dp[x][y]);
    }
  }

  public static void main(String[] args) {
    LeetCode778SwimInRisingWater one = new LeetCode778SwimInRisingWater();
    int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
    System.out.println(one.swimInWater_BFS(grid));
        
  }

}
