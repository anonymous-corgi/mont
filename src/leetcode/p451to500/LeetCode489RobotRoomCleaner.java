package leetcode.p451to500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import algorithm.base.Robot;

public class LeetCode489RobotRoomCleaner {
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  private int x;
  private int y;
  private int dir = 0;
  //Map<Integer, Set<Integer>> is a good data structure for unknown boundary place.
  private Map<Integer, Set<Integer>> map = new HashMap<>();
  
  public void cleanRoom(Robot robot) {
    //Each time calling cleanRoom means you are currently in (x, y).
    Set<Integer> ySet =  map.getOrDefault(x, new HashSet<Integer>());
    ySet.add(y);
    map.put(x, ySet);
    robot.clean();
    
    for (int i = 0; i < 4; i++) {
      int nx = x + DIRS[dir][0];
      int ny = y + DIRS[dir][1];
      if ((!map.containsKey(nx) || !map.get(nx).contains(ny)) && robot.move()) {
        //Update the coordinate right after you move forward. And then clean the room.
        x = nx;
        y = ny;
        cleanRoom(robot);
        //Because the direction has turn 180 degree After the cleanRoom() is called,
        //we just need to call move() to move back to the original position.
        robot.move();      
        x += DIRS[dir][0];
        y += DIRS[dir][1];
        //If this is not the 4th Direction, we turn left to clean the next direction.
        //Otherwise, we turn right to make sure we turn 180 degree to the direction we just enter.
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
