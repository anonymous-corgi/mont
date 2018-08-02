package leetcode.p301to350;

import java.util.ArrayList;
import java.util.List;
import basicclass.Point;

public class LeetCode305NumberOfIslandsII {
  
  private final int[][] DIRS = {{0, 1},{1, 0},{0, -1},{-1, 0}};
  private int[] ufp;
  public List<Integer> numIslands2(int n, int m, Point[] operators) {
    // write your code here
    List<Integer> res = new ArrayList<>();
    if (operators == null || operators.length == 0) {
      return res;
    }
    int count = 0;
    ufp = new int[n * m];
    for (int i = 0; i < ufp.length; i++) {
      ufp[i] = -1;
    }
    for (int i = 0; i < operators.length; i++) {
      Point op = operators[i];
      int index = op.x * m + op.y;
      if (ufp[index] != -1) {
        res.add(count);
        continue;
      }
      count++;
      ufp[index] = index;
      for (int j = 0; j < 4; j++) {
        int nX = op.x + DIRS[j][0];
        int nY = op.y + DIRS[j][1];
        if (nX >= 0 && nX < n && nY >= 0 && nY < m) {
          int nIndex = nX * m + nY;
          if (ufp[nIndex] != -1 && !isConnected(index, nIndex)) {
            count--;
          }
        }
      }
      res.add(count);
    }
    return res;
  }
  
  private int find(int n) {
    return ufp[n] == n ? n : (ufp[n] = find(ufp[n]));
  }
  
  private boolean isConnected(int a, int b) {
    int rA = find(a);
    int rB = find(b);
    if (rA == rB) {
      return true;
    } else {
      if (rA < rB) {
        ufp[rB] = rA;
      } else {
        ufp[rA] = rB;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
