package lintcode.p551to600;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LintCode574BuildPostOffice {
  
  public int shortestDistance(int[][] grid) {
    int n = grid.length;
    if (n == 0)
      return -1;
    
    int m = grid[0].length;
    if (m == 0)
      return -1;
    
    List<Integer> sumx = new ArrayList<Integer>();
    List<Integer> sumy = new ArrayList<Integer>();
    List<Integer> x = new ArrayList<Integer>();
    List<Integer> y = new ArrayList<Integer>();
    
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (grid[i][j] == 1) {
          x.add(i);
          y.add(j);
        }
      }
    }
    
    Collections.sort(x);
    Collections.sort(y);
    
    int total = x.size();
    
    sumx.add(0);
    sumy.add(0);
    for (int i = 0; i < total; ++i) {
      sumx.add(sumx.get(i) + x.get(i));
      sumy.add(sumy.get(i) + y.get(i));
    }
    
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (grid[i][j] == 0) {
          int cost_x = get_cost(x, sumx, i, total);
          int cost_y = get_cost(y, sumy, j, total);
          if (cost_x + cost_y < result) {
            result = cost_x + cost_y;
          }
        }
      }
    }
    return result;
  }
  
  public int get_cost(List<Integer> x, List<Integer> sum, int pos, int N) {
    if (N == 0) {
      return 0;
    }
    if (x.get(0) >= pos) {
      return sum.get(N) - pos * N;
    }
    if (x.get(N - 1) <= pos) {
      return pos * N - sum.get(N);
    }
    
    //Find the last position l where x.get(l) <= pos.
    int l = 0, r = N - 1;
    while (l < r) {
      int mid = l + (r - l + 1) / 2;
      if (x.get(mid) <= pos) {
        l = mid;
      } else {
        r = mid - 1;
      }
    }
    //(sum.get(N) - sum.get(l + 1) - pos * (N - l - 1)) is the distance 
    //from pos to all the points in the right of pos.
    //((l + 1) * pos - sum.get(l + 1)) is the distance
    //from pos to all the points in the left of pos(including points in pos).
    return (sum.get(N) - sum.get(l + 1) - pos * (N - l - 1)) + 
                ((l + 1) * pos - sum.get(l + 1));
  }
  
  public static void main(String[] args) {
    //Expected Result: 6
    int[][] grid = {{0, 1, 0, 0,}, {1, 0, 1, 1}, {0, 1, 0, 0}};
    LintCode574BuildPostOffice one = 
        new LintCode574BuildPostOffice();
    System.out.println(one.shortestDistance(grid)); 
  }
  
}
