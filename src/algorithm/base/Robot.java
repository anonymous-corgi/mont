package algorithm.base;

public class Robot {
  private final String[] DIRS_SYMBOL = {"↑","→","↓","←"};
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  private int x;
  private int y;
  private int dir = 0;
  private final int[][] map;
  private int unCleaned;
  
  public Robot(int[][] map, int x, int y){
    this.map = map;
    this.x = x;
    this.y = y;
    this.unCleaned = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 1) {
          unCleaned++;
        }
      }
    }
    locAndDir();
  }
  
  public boolean move() {
    int nx = x + DIRS[dir][0];
    int ny = y + DIRS[dir][1];
    if (inBound(nx, ny) && map[nx][ny] != -1) {
      x = nx;
      y = ny;
      locAndDir();
      return true;
    }
    System.out.println("Row: " + x + ", Col: " + y + ", Dir: " + DIRS_SYMBOL[dir] + " ×");
    return false;
  };
  
  public void turnLeft() {
    dir--;
    if (dir < 0) {
      dir += 4;
    }
    locAndDir();
  };
  
  public void turnRight() {
    dir++;
    dir %= 4;
    locAndDir();
  };
  
  public void clean() {
    if (map[x][y] == 1) {
      unCleaned--;
    }
    map[x][y] = 0;
    printMap();
  };
  
  private void printMap() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 1) {
          System.out.print("*" + "\t");
        } else if (map[i][j] == 0) {
          System.out.print("□" + "\t");
        } else {
          System.out.print("■" + "\t");
        }
      }
      System.out.println();
    }
  }
  
  public boolean isDone() {
    printMap();
    return unCleaned == 0;
  }
  
  private boolean inBound(int x, int y) {
    return (x >= 0 && x < map.length && y >= 0 && y < map[0].length);
  }

  private void locAndDir() {
    System.out.println("Row: " + x + ", Col: " + y + ", Dir: " + DIRS_SYMBOL[dir]);
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[][] map = {{1,0,-1},{1,-1,1},{1,1,1}};
    Robot one = new Robot(map, 0, 0);
    one.isDone();
  }

}
