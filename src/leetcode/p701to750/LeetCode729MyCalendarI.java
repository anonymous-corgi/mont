package leetcode.p701to750;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode729MyCalendarI {
  
  private final TreeMap<Integer, Integer> timeline = new TreeMap<>();
  
  public boolean book(int start, int end) {
      Map.Entry<Integer, Integer> prev = timeline.floorEntry(start);
      Map.Entry<Integer, Integer> next = timeline.ceilingEntry(start);
      if ((prev == null || prev.getValue() <= start) 
            && (next == null || next.getKey() >= end)) {
          timeline.put(start, end);
          return true;
      }
      return false;
  }

}
