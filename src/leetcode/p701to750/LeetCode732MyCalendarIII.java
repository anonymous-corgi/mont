package leetcode.p701to750;

import java.util.TreeMap;

public class LeetCode732MyCalendarIII {
  
  private TreeMap<Integer, Integer> timeline = new TreeMap<>();
  
  public int book(int s, int e) {
    timeline.put(s, timeline.getOrDefault(s, 0) + 1);
    timeline.put(e, timeline.getOrDefault(e, 0) - 1);
    int count = 0;
    int res = 0;
    for (int v : timeline.values()) {
      res = Math.max(res, count += v);
    }
    return res;
  }

  public static void main(String[] args) {
    LeetCode732MyCalendarIII one =
        new LeetCode732MyCalendarIII();
    int[][] times = {{10, 11}, {10, 13}, {5, 6}, {3, 6}, {4, 13}, {1, 16}};
    for (int i = 0; i < times.length; i++) {
      System.out.println(one.book(times[i][0], times[i][1]));
    }
  }

}
