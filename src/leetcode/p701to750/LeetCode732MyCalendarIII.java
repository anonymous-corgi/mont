package leetcode.p701to750;

import java.util.TreeMap;

public class LeetCode732MyCalendarIII {
  
  private TreeMap<Integer, Integer> timeline = new TreeMap<>();
  public int book(int s, int e) {
    timeline.put(s, timeline.getOrDefault(s, 0) + 1); // 1 new event will be starting at [s]
    timeline.put(e, timeline.getOrDefault(e, 0) - 1); // 1 new event will be ending at [e];
    int count = 0;
    int res = 0;
    for (int v : timeline.values())
      res = Math.max(res, count += v);
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
