package leetcode.p501to550;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode546RemoveBoxes {
  
  private final int N = 10;
  private Map<Integer, Integer> cache;
  
  public int removeBoxes(int[] boxes) {
    if (boxes == null || boxes.length == 0) {
      return 0;
    }
    cache = new HashMap<Integer, Integer>();
    int[][] cBoxes = getCBoxes(boxes);
    return dfs(cBoxes, 0, cBoxes.length - 1, 0);
  }
  
  private int dfs(int[][] cBoxes, int start , int end, int addup) {
    if (start > end) { return 0; }
    int code = (addup << N * 2) + (start << N) + end; 
    if (cache.containsKey(code)) {
      return cache.get(code);
    }
    
    addup += cBoxes[start][1];
    int res = addup * addup + dfs(cBoxes, start + 1, end, 0);
    
    for (int i = start + 1; i <= end; i++) {
      if (cBoxes[i][0] == cBoxes[start][0]) {
        res = Math.max(res, dfs(cBoxes, start + 1, i - 1, 0) + dfs(cBoxes, i, end, addup));
      }
    }
    
    cache.put(code, res);
    return res;
  }
  
  private int[][] getCBoxes(int[] boxes) {
    List<int[]> cList = new ArrayList<>();
    int[] c = new int[]{boxes[0], 1};
    cList.add(c);
    for (int i = 1; i < boxes.length; i++) {
      if (boxes[i] != c[0]) {
        c = new int[]{boxes[i], 1};
        cList.add(c);
      } else {
        c[1]++;
      }
    }
    return cList.toArray(new int[0][2]);
  }
  
}
