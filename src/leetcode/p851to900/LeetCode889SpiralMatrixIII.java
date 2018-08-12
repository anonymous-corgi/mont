package leetcode.p851to900;

import java.util.ArrayList;
import java.util.List;

public class LeetCode889SpiralMatrixIII {
  
  private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  public int[][] spiralMatrixIII(int R, int C, int r, int c) {
    int total = R * C;
    int dir = 1;
    List<int[]> res = new ArrayList<>(total);
    res.add(new int[]{r, c});
    for (int i = 1; ; i++) {
      for (int j = 0; j < i; j++) {
        r += DIRS[dir][0];
        c += DIRS[dir][1];
        if (valid(r, c, R, C)) {
          res.add(new int[]{r, c});
        }
        if (res.size() == total) {
          return res.toArray(new int[0][2]);
        }
      }
      dir = (dir + 1) % 4;
      for (int j = 0; j < i; j++) {
        r += DIRS[dir][0];
        c += DIRS[dir][1];
        if (valid(r, c, R, C)) {
          res.add(new int[]{r, c});
        }
        if (res.size() == total) {
          return res.toArray(new int[0][2]);
        }
      }
      dir = (dir + 1) % 4;
    }
  }
  
  private boolean valid(int r, int c, int R, int C) {
    return r >= 0 && r < R && c >= 0 && c < C;
  }

  public static void main(String[] args) {

  }

}
