package leetcode.p301to350;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode317ShortestDistanceFromAllBuildings {
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  private int rows;
  private int cols;
  public int shortestDistance(int[][] grid) {
    if (grid == null || grid.length == 0 
        || grid[0] == null || grid[0].length == 0) {
      return -1;
    }
    int flag = 1;
    int res = Integer.MAX_VALUE;
    rows = grid.length;
    cols = grid[0].length;
    int[][] dis = new int[rows][cols];
    int[][] flags = new int[rows][cols];
    List<int[]> houses = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          houses.add(new int[]{i, j});
        }
      }
    }
    for (int[] house : houses) {
      bfs(house, grid, dis, flags, flag);
      flag <<= 1;
    }
    flag -= 1;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 0 && flags[i][j] == flag) {
          res = Math.min(res, dis[i][j]);
        }
      }
    }
    
    return res == Integer.MAX_VALUE ? -1 : res;
  }
  
  private void bfs(int[] start, int[][] grid, int[][] dis, int[][] flags, final int flag) {
    Queue<int[]> taskList = new LinkedList<>();
    int d = 0;
    flags[start[0]][start[1]] |= flag;
    taskList.offer(start);
    int[] p = null;
    while (!taskList.isEmpty()) {
      for (int i = 0, iLen = taskList.size(); i < iLen; i++) {
        p = taskList.poll();
        dis[p[0]][p[1]] += d;
        
        for (int j = 0; j < 4; j++) {
          int nx = p[0] + DIRS[j][0];
          int ny = p[1] + DIRS[j][1];
          if (nx >= 0 && nx < rows && ny >=0 && ny < cols 
              && grid[nx][ny] != 2 && (flags[nx][ny] & flag) == 0) {
            flags[nx][ny] |= flag;
            taskList.offer(new int[]{nx, ny});
          }
        }
      }      
      d++;
    }
  }
  
//  private 

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode317ShortestDistanceFromAllBuildings one
     = new LeetCode317ShortestDistanceFromAllBuildings();
    int[][] grid = {{0,1,0,0,0},{1,0,0,2,1},{0,1,0,0,0}};
    System.out.println(one.shortestDistance(grid));

  }

}
