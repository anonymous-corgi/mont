package algorithm.interview.google.oa;

import static org.junit.Assert.*;
import java.util.TreeSet;
import org.junit.Test;

public class NextTime {
  
  public String nextClosestTime(String time) {
    if (time == null || time.length() != 5) {
      return time;
    }
    StringBuilder sb = new StringBuilder(time);
    sb.delete(2, 3);
    TreeSet<Integer> set = new TreeSet<Integer>();
    int[] timeArr = new int[4];
    for (int i = 0; i < 4; i++) {
      timeArr[i] = Integer.parseInt(sb.substring(i, i + 1));
      set.add(timeArr[i]);
    }
    
    if (set.size() == 1) {
      return time;
    }
    
    int lowest = set.first();
    Integer cursor;
    for (int i = 3; i >= 0; i--) {
      cursor = set.higher(timeArr[i]);
      if (cursor != null) {
        sb.replace(i, i + 1, "" + cursor);
        if (isValid(sb, i)) {
          sb.insert(2, ":");
          return sb.toString();        				
        }
      }
      sb.replace(i, i + 1, "" + lowest);
    }
    sb.insert(2, ":");
    return sb.toString();
  }
  
  private boolean isValid(StringBuilder sb, int i){
    if (i / 2 < 1) {
      return Integer.parseInt(sb.substring(0, 2)) < 24;
    } else {
      return Integer.parseInt(sb.substring(2, 4)) < 60;
    }
  }
  
  
  @Test
  public void testNextClosestTime() {
    String[] tests = {"15:59", "10:11", "01:21"};
    String[] expecteds = {"19:11", "11:00", "01:22"};
    for (int i = 0, len = tests.length; i < len; i++) {
      assertEquals(expecteds[i], nextClosestTime(tests[i]));
    }
  }
  
}
