package leetcode.p501to550;

public class LeetCode505TheMazeII {
  
  private final int[][][] DIRS = {{{-1, 0}, {1, 0}}, {{0, 1}, {0, -1}}};
  private int rows;
  private int cols;
  public int shortestDistance(int[][] maze, int[] start, int[] des) {
    rows = maze.length;
    cols = maze[0].length;
    dfs(maze, start[0], start[1], 0, -1);
    dfs(maze, start[0], start[1], 1, -1);
    return -maze[des[0]][des[1]] - 1;
  }
  
  private void dfs(int[][] maze, int sx, int sy, int d, int dis) {
    maze[sx][sy] = dis; 
    for (int[] dir : DIRS[d]) {
      int nx = sx;
      int ny = sy;
      int nDis = dis;
      while (inBound(nx + dir[0], ny + dir[1]) 
          && maze[nx + dir[0]][ny + dir[1]] != 1) {
        nx = nx + dir[0];
        ny = ny + dir[1];
        nDis--;
      }
      if (maze[nx][ny] == 0 || nDis > maze[nx][ny]) {
        dfs(maze, nx, ny, (d + 1) % 2, nDis);
      }
    }
  }
  
  private boolean inBound(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode505TheMazeII one = new LeetCode505TheMazeII();
    int[][] maze = {{1,0,1,1},{0,0,0,1},{0,0,0,0},{1,0,0,1}};
    int[] start = {0,1};
    int[] des = {2,3};
    
//    int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
//    int[] start = {0,4};
//    int[] des = {3,2};
    System.out.println(one.shortestDistance(maze, start, des));
  }

}
