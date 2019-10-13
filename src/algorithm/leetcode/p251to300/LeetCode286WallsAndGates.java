package algorithm.leetcode.p251to300;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode286WallsAndGates {
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  public void wallsAndGates(int[][] rooms) {
    if (rooms == null) {
      return;
    }
    Queue<int[]> taskList = new LinkedList<>();
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[i].length; j++) {
        if (rooms[i][j] == 0) {
          taskList.offer(new int[]{i, j});
        }
      }
    }
    while (!taskList.isEmpty()) {
      int[] cursor = taskList.poll();
      for (int[] dir : DIRS) {
        int nx = cursor[0] + dir[0];
        int ny = cursor[1] + dir[1];
        if (nx >= 0 && nx < rooms.length 
            && ny >= 0 && ny < rooms[0].length) {
          if (rooms[nx][ny] > rooms[cursor[0]][cursor[1]] + 1) {
            rooms[nx][ny] = rooms[cursor[0]][cursor[1]] + 1;
            taskList.offer(new int[]{nx, ny});
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
