package algorithm.base;

import java.util.ArrayList;
import java.util.List;
import com.sun.istack.internal.Nullable;

public class Interval {
  
  public int start;
  public int end;
  
  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
  
  @Nullable
  public static Interval deserialize(String data) {
    if (data == null || data.length() < 5) {
      System.out.println("<Error> The input data is invalid.");
      return null;
    }
    String[] nums = data.substring(1, data.length() - 1).split(",");
    if (nums.length != 2) {
      System.out.println("<Error> The input data is invalid.");
      return null;
    }
    int a = Integer.parseInt(nums[0]);
    int b = Integer.parseInt(nums[1]);
    if (a > b) {
      System.out.println("<Error> The input data is invalid: start > end.");
      return null;
    }
    return new Interval(a, b);
  }
  
  public static List<Interval> deserializeToList(String data) {
    if (data == null || data.length() < 7) {
      System.out.println("<Error> The input data is invalid.");
      return null;
    }
    List<Interval> list = new ArrayList<>();
    String[] intervals = data.substring(1, data.length() - 1).split(",");
    if (intervals.length < 1) {
      System.out.println("<Error> The input data is invalid.");
      return null;
    }
    for (int i = 0; i < intervals.length; i++) {
      Interval interval = deserialize(intervals[i]);
      if (interval != null) {
        list.add(interval);
      }
    }
    return list;
  }
  
}
