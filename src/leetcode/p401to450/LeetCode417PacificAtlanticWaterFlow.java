package leetcode.p401to450;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode417PacificAtlanticWaterFlow {

  public List<int[]> pacificAtlantic(int[][] map) {
    List<int[]> res = new ArrayList<>();
    if (map == null || map.length == 0
        || map[0] == null || map[0].length == 0) {
      return res;
    }
    int rows = map.length;
    int cols = map[0].length;
    boolean[][] fp = new boolean[rows][cols];
    boolean[][] fa = new boolean[rows][cols];
    Queue<int[]> tp = new LinkedList<>();
    Queue<int[]> ta = new LinkedList<>();
    for (int i = 0; i < rows; i++) {
      fp[i][0] = true;
      fa[i][cols - 1] = true;
      tp.offer(new int[]{i, 0});
      ta.offer(new int[]{i, cols - 1});
    }
    for (int j = 0; j < cols; j++) {
      fp[0][j] = true;
      fa[rows - 1][j] = true;      
      tp.offer(new int[]{0, j});
      ta.offer(new int[]{rows - 1, j});
    }
    bfs(map, fa, ta);
    bfs(map, fp, tp);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (fa[i][j] && fp[i][j]) {
          res.add(new int[]{i, j});
        }
      }
    }
    return res;
  }
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  
  private void bfs(int[][] map, boolean[][] f, Queue<int[]> taskList) {
    while (!taskList.isEmpty()) {
      int[] cur = taskList.poll();
      for (int[] dir : DIRS) {
        int[] np = new int[]{cur[0] + dir[0], cur[1] + dir[1]};
        if (np[0] >= 0 && np[0] < map.length && np[1] >= 0 && np[1] < map[0].length) {
          if (!f[np[0]][np[1]] && map[cur[0]][cur[1]] <= map[np[0]][np[1]]){
            f[np[0]][np[1]] = true;
            taskList.offer(np);
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    LeetCode417PacificAtlanticWaterFlow one
        = new LeetCode417PacificAtlanticWaterFlow();
    int[][] map = {{1,2,3},{8,9,4},{7,6,5}};
    one.pacificAtlantic(map);
  }
}
