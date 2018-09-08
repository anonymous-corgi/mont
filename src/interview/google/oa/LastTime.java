package interview.google.oa;

import static org.junit.Assert.*;
import java.util.TreeSet;
import org.junit.Test;

public class LastTime {
  
//  /**Solution1: */		
  public String lastClosestTime(String time) {
    if (time == null || time.length() != 5) {
      return time;
    }
    StringBuilder sb = new StringBuilder(time);
    TreeSet<Integer> set = new TreeSet<Integer>();
    int[] tArr = new int[4];
    sb.delete(2, 3);
    for (int i = 0; i < 4; i++) {
      tArr[i] = Integer.parseInt(sb.substring(i, i + 1));
      set.add(tArr[i]);
    }
    
    if (set.size() == 1) {
      return time;
    }
    
    final int highest= set.last();        
    for (int i = 3; i >= 0; i--) {
      Integer lo = set.lower(tArr[i]);
      if (lo != null) {
        sb.replace(i, i + 1, "" + lo);
        if (isValid(sb, i)) {                    
          sb.insert(2, ":");
          return sb.toString();
        }
      }
      sb.replace(i, i + 1, "" + highest);
    }
    sb.insert(2, ":");
    return sb.toString();
  }
  
  private static boolean isValid(StringBuilder sb, int position) {
    if (position / 2 == 0) {
      return Integer.parseInt(sb.substring(0, 2)) < 24;
    } else {
      return Integer.parseInt(sb.substring(2, 4)) < 60;
    }
  }
  
//  /**Solution2: */	
//	private int[] highestNum = {3, 4, 6, 10};
//	
//	public String lastClosestTime(String time) {
//		if (time == null || time.length() != 5) {
//			return time;
//		}
//		StringBuilder sb= new StringBuilder(time);
//		TreeSet<Integer> set = new TreeSet<>();
//		int[] timeArr = new int[4];
//		int index = 0;
//		for (int i = 0; i < 5; i++) {
//			if (i == 2) {
//				continue;
//			}
//			timeArr[index] = Integer.parseInt(time.substring(i, i + 1));
//			set.add(timeArr[index]);
//			index++;
//		}
//		
//		if (set.size() == 1) {
//			return time;
//		}
//		
//		Integer cursor;
//		index = 3;
//		for (int i = 4; i >= 0; i--) {
//			if (i == 2) {
//				continue;
//			}
//			cursor = set.lower(timeArr[index]);
//			if (cursor != null) {
//				sb.replace(i, i + 1, "" + cursor);
//				return sb.toString();
//			} else {
//				Integer largest = set.lower(highestNum[index]);
//				sb.replace(i, i + 1, "" + largest);
//			}
//			index--;
//		}
//		return sb.toString();
//	}
  
  
  @Test
  public void testLastClosestTime() {
    String[] tests = {"23:59", "22:55", "22:11"};
    String[] expecteds = {"23:55", "22:52", "21:22"};
    for (int i = 0, len = tests.length; i < len; i++) {
      assertEquals(expecteds[i], lastClosestTime(tests[i]));
    }
  }
  
}
