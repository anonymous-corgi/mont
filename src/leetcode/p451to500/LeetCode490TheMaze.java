package leetcode.p451to500;

public class LeetCode490TheMaze {
  
  private final int[][][] DIRS = {{{-1, 0}, {1, 0}}, {{0, 1}, {0, -1}}};
  private int rows;
  private int cols;
  public boolean hasPath(int[][] maze, int[] start, int[] des) {
    // write your code here
    rows = maze.length;
    cols = maze[0].length;
    dfs(maze, start[0], start[1], 0);
    dfs(maze, start[0], start[1], 1);
    return maze[des[0]][des[1]] == -1;
  }
  
  private void dfs(int[][] maze, int sx, int sy, int d) {
    maze[sx][sy] = -1; 
    for (int[] dir : DIRS[d]) {
      int nx = dir[0] + sx;
      int ny = dir[1] + sy;
      if (!inBound(nx, ny) || maze[nx][ny] == 1) {
        continue;
      }
      while (inBound(nx + dir[0], ny + dir[1]) 
          && maze[nx + dir[0]][ny + dir[1]] != 1) {
        nx = nx + dir[0];
        ny = ny + dir[1];
      }
      if (maze[nx][ny] != -1) {
        dfs(maze, nx, ny, (d + 1) % 2);
      }
    }
  }
  
  private boolean inBound(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode490TheMaze one = new LeetCode490TheMaze();
    int[][] maze = {{1,0,1,1},{0,0,0,1},{0,0,0,0},{1,0,0,1}};
    int[] start = {0,1};
    int[] des = {2,3};
    
//    int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
//    int[] start = {0,4};
//    int[] des = {3,2};
    System.out.println(one.hasPath(maze, start, des));
  }

}
