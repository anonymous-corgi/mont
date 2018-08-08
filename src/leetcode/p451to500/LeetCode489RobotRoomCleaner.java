package leetcode.p451to500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import basicclass.Robot;

public class LeetCode489RobotRoomCleaner {
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  private int x;
  private int y;
  private int dir = 0;
  private Map<Integer, Set<Integer>> map = new HashMap<>();
  
  public void cleanRoom(Robot robot) {
    Set<Integer> set =  map.getOrDefault(x, new HashSet<Integer>());
    if (set.contains(y)) {
      return;
    }
    set.add(y);
    map.put(x, set);
    robot.clean();
    
    for (int i = 0; i < 4; i++) {
      int nx = x + DIRS[dir][0];
      int ny = y + DIRS[dir][1];
      if ((!map.containsKey(nx) || !map.get(nx).contains(ny)) && robot.move()) {
        x = nx;
        y = ny;
        cleanRoom(robot);
        robot.move();      
        x += DIRS[dir][0];
        y += DIRS[dir][1];
        if (i != 3) {
          turnLeft(robot);
        } else {
          turnRight(robot);
        }
      } else {
        if (i != 3) {
          turnRight(robot);
        } else {
          turnLeft(robot);
        }
      }
    }
  }
  
  private void turnLeft(Robot robot) {
    dir--;
    dir = dir < 0 ? dir + 4 : dir;
    robot.turnLeft();
  }
  
  private void turnRight(Robot robot) {
    dir++;
    dir %= 4;
    robot.turnRight();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[][] map = {{1,1,1,1,1,-1,1,1},
        {1,1,1,1,1,-1,1,1},
        {1,-1,1,1,1,1,1,1},
        {-1,-1,-1,1,-1,-1,-1,-1},
        {1,1,1,1,1,1,1,1}};
    Robot robot = new Robot(map, 1, 3);
    LeetCode489RobotRoomCleaner one = new LeetCode489RobotRoomCleaner();
    one.cleanRoom(robot);
    System.out.println(robot.isDone());
  }

}
