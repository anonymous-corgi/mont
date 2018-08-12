package leetcode.p451to500;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode499TheMazeIII {
  
  private final int[][][] DIRS = {{{1, 0}, {-1, 0}}, { {0, -1}, {0, 1}}};
  private final char[][] DIRS_C = {{'d', 'u'}, {'l', 'r'}};
  private int min = Integer.MIN_VALUE;
  private Queue<String> res = new LinkedList<>();
  private int rows;
  private int cols;
  public String findShortestWay(int[][] maze, int[] start, int[] des) {    
    rows = maze.length;
    cols = maze[0].length;
    StringBuilder sb = new StringBuilder();
    dfs(maze, start[0], start[1], des[0], des[1], 0, -1, sb);
    dfs(maze, start[0], start[1], des[0], des[1], 1, -1, sb);
    return res.isEmpty() ? "impossible" : res.poll();
  }  
  
  private void dfs(int[][] maze, int sx, int sy, int desx, int desy,
                    int d, int dis, StringBuilder sb) {
    maze[sx][sy] = dis; 
    for (int i = 0; i < 2; i++) {
      int[] dir = DIRS[d][i];
      int nx = sx;
      int ny = sy;
      int nDis = dis;
      while (inBound(nx + dir[0], ny + dir[1]) 
          && maze[nx + dir[0]][ny + dir[1]] != 1) {
        nx = nx + dir[0];
        ny = ny + dir[1];
        nDis--;    
        if (nx == desx && ny == desy && nDis >= min) {
          if (nDis > min) {
            res.clear();
            min = nDis;
          }
          sb.append(DIRS_C[d][i]);
          res.offer(sb.toString());
          sb.deleteCharAt(sb.length() - 1);
        }
      }
      if (maze[nx][ny] == 0 || nDis > maze[nx][ny]) {
        sb.append(DIRS_C[d][i]);
        dfs(maze, nx, ny, desx, desy, (d + 1) % 2, nDis, sb);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
  
  private boolean inBound(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode499TheMazeIII one = new LeetCode499TheMazeIII();
//    int[][] maze = {{1,0,1,1},{0,0,0,1},{0,0,0,0},{1,0,0,1}};
//    int[] start = {0,1};
//    int[] des = {2,3};
    
//    int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
//    int[] start = {4,3};
//    int[] des = {0,1};
    
  int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
  int[] start = {0,4};
  int[] des = {3,2};
    System.out.println(one.findShortestWay(maze, start, des));
  }


}
