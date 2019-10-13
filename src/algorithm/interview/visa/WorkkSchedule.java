package algorithm.interview.visa;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class WorkkSchedule {
  
  interface Method {
    List<String> findAllSchedule(String pattern, int week_hour, int max_day_hour);
  }
  
  public static class DFS_method implements Method {

    @Override
    public List<String> findAllSchedule(String pattern, int week_hour, int max_day_hour) {
      int sum = 0;
      int[] pat = new int[]{-1, -1, -1, -1, -1, -1, -1};
      List<String> res = new ArrayList<>();
      for (int i = 0; i < pattern.length(); i++) {
        if (pattern.charAt(i) != '?') {
          pat[i] = Integer.parseInt(pattern.substring(i, i + 1));
          sum += pat[i];
        }
      }
      helper(0, sum, max_day_hour, week_hour, pat, new StringBuilder(), res);
      return res; 
    }
    
    private void helper(int pos, int sum, int dayMax, int weekMax, int[] pat, StringBuilder sb, List<String> res) {
      if (pos == 7) {
        if (sum == weekMax) {
          res.add(sb.toString());
        }
        return;
      }
      
      if (pat[pos] != -1) {
        sb.append(pat[pos]);
        helper(pos + 1, sum , dayMax, weekMax, pat, sb, res);
        sb.deleteCharAt(sb.length() - 1);
      } else {
        for (int i = 0; i <= dayMax; i++) {
          if (sum + i > weekMax) {
            return;
          }
          sb.append(i);
          helper(pos + 1, sum + i, dayMax, weekMax, pat, sb, res);
          sb.deleteCharAt(sb.length() - 1);
        }
      }
    }
    
  }
  
  
//  public static class BackTracking_method implements Method {
//
//    @Override
//    public List<String> findAllSchedule(String pattern, int week_hour, int max_day_hour) {
//      int sum = 0;
//      int[] pat = new int[7];
//      List<String> res = new ArrayList<>();
//      for (int i = 0; i < pattern.length(); i++) {
//        if (pattern.charAt(i) != '?') {
//          pat[i] = Integer.parseInt(pattern.substring(i, i + 1));
//          sum += pat[i];
//        }
//      }
//      
//      return res; 
//    }
//    
//    private List<String> helper(int pos, int sum, int dayMax, int weekMax, int[] pat) {
//      if (sum == 0) {
//        return pos == 7 ? Arrays.asList("") : Collections.emptyList();
//      }
//      List<String> res = new ArrayList<>();
//      if (pat[pos] != 0) {
//        List<String> pList = helper(pos + 1, sum, );
//        for (String each : )
//        helper(pos + 1, sum , dayMax, weekMax, pat, sb, res);
//      } else {
//        for (int i = 0; i < dayMax; i++) {
//          if (sum + i > weekMax) {
//            return;
//          }
//          sb.append(i);
//          helper(pos + 1, sum + i, dayMax, weekMax, pat, sb, res);
//          sb.deleteCharAt(sb.length() - 1);
//        }
//      }
//    }
//
//    
//  }
  
  
  
  private Method getInstance() {
    return new DFS_method();
  }
  
  public List<String> findAllSchedule(String pattern, int week_hour, int max_day_hour) {
    return getInstance().findAllSchedule(pattern, week_hour, max_day_hour);
  }
  
  
  @Test
  public void testFindAllSchedule1() {
    String pattern = "???8???";
    int week_hour = 56; 
    int max_day_hour = 8;
    String[] res = {"8888888"};
    assertArrayEquals(res, findAllSchedule(pattern, week_hour, max_day_hour).toArray(new String[0]));
  }
  
  @Test
  public void testFindAllSchedule2() {
    String pattern = "??2??00";
    int week_hour = 3; 
    int max_day_hour = 2;
    String[] res = {"0020100", "0021000", "0120000", "1020000"};
    assertArrayEquals(res, findAllSchedule(pattern, week_hour, max_day_hour).toArray(new String[0]));
  }
  
  public static void main(String[] args) {
    String pattern = "??2??00";
    int week_hour = 3; 
    int max_day_hour = 2;
    WorkkSchedule one = new WorkkSchedule();
    List<String> res = one.findAllSchedule(pattern, week_hour, max_day_hour);
    System.out.println(res);
  }

}
