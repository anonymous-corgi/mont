package algorithm.interview.google.oa;

import org.junit.Test;
import static org.junit.Assert.*;

public class LastTimeNoRepeated {
  
  private String result;
  private int oriMins;
  private int minDiff = Integer.MAX_VALUE;
  
  public String lastClosestTime(String time) {
    if (time == null || time.length() != 5) {
      return "";
    }
    StringBuilder sb = new StringBuilder(time);
    int[] t = new int[4];
    sb.delete(2, 3);
    for (int i = 0; i < 4; i++) {
      t[i] = Integer.parseInt(sb.substring(i, i + 1));
    }
    oriMins = countMin(t);
    result = time;
    dfs(t, new boolean[4], new int[4], 0);
    
    return result;
  }
  
  private void dfs(int[] res, boolean[] isUsed, int[] t, int position) {
    if (position == 4) {
      int mins = countMin(t);
      int diff = mins <= oriMins ?  oriMins - mins : 1440 + oriMins - mins;
      if (diff != 0 && diff < minDiff) {
        minDiff = diff;
        result = "" + t[0] + t[1] + ":" + t[2] + t[3];
      }
      return;
    }
    
    for (int i = 0; i < 4; i++) {
      if (isUsed[i]) {
        continue;
      }
      if (position == 0 && res[i] > 2) {
        continue;
      }
      if (position == 1 && (t[0] * 10 + res[i] > 23)) {
        continue;
      }
      if (position == 2 && res[i] > 5) {
        continue;
      }
      isUsed[i] = true;
      t[position] = res[i];
      dfs(res, isUsed, t, position + 1);
      isUsed[i] = false;
    }
  }
  
  private int countMin(int[] t) {
    return ((t[0] * 10 + t[1]) * 60 + t[2] * 10 + t[3]);
  }
  
  
  @Test
  public void testLastClosestTime() {
    String[] tests = {"23:59", "22:55", "22:11", "05:13", "01:11", "01:33", "02:22"};
    String[] expecteds = {"23:59", "22:55", "21:21", "03:51", "11:10", "13:30", "22:20"};
    for (int i = 0, len = tests.length; i < len; i++) {
      assertEquals(expecteds[i], lastClosestTime(tests[i]));
    }
  }
  
}
