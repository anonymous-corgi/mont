package leetcode.p701to750;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode731MyCalendarI {
  
  private final TreeMap<Integer, Integer> map;

  public LeetCode731MyCalendarI() {
      map = new TreeMap<>();
  }
  
  public boolean book(int start, int end) {
      Map.Entry<Integer, Integer> prev = map.floorEntry(start);
      Map.Entry<Integer, Integer> next = map.ceilingEntry(start);
      if ((prev == null || prev.getValue() <= start) 
      && (next == null || next.getKey() >= end)) {
          map.put(start, end);
          return true;
      }
      return false;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
