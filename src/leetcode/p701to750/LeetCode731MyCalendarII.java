package leetcode.p701to750;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeetCode731MyCalendarII {
  
  //Method 1:
  public static class TreeMap_method {
    
    private TreeMap<Integer, Integer> map = new TreeMap<>();
    
    public boolean book(int s, int e) {
      map.put(s, map.getOrDefault(s, 0) + 1);
      map.put(e, map.getOrDefault(e, 0) - 1);
      int cnt = 0;
      for (Integer v : map.values()) {
        cnt += v;
        if (cnt == 3) {
          map.put(s, map.get(s) - 1);
          map.put(e, map.get(e) + 1);
          return false;
        }
      }
      return true;
    }
    
  }
  
  //Method 2:
  public static class List_method {
    
    private List<int[]> books = new ArrayList<>();
    
    public boolean book(int s, int e) {
      OVerlap overlaps = new OVerlap();
      for (int[] b : books)
        if (Math.max(b[0], s) < Math.min(b[1], e)) {
          // overlap exist
          if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e))) {
            return false; // overlaps overlapped
          }
        }
      books.add(new int[]{ s, e });
      return true;
    }
    
    private static class OVerlap {
      List<int[]> books = new ArrayList<>();
      public boolean book(int start, int end) {
        for (int[] b : books)
          if (Math.max(b[0], start) < Math.min(b[1], end)) return false;
        books.add(new int[]{ start, end });
        return true;
      }
    }
    
  }

  public static void main(String[] args) {
    LeetCode731MyCalendarII.List_method one =
        new LeetCode731MyCalendarII.List_method();
    int[][] times = {{10, 11}, {10, 13}, {5, 6}, {3, 6}, {4, 13}, {1, 16}};
    for (int i = 0; i < times.length; i++) {
      System.out.println(one.book(times[i][0], times[i][1]));
    }
  }

}
