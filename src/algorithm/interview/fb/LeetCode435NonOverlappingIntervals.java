package algorithm.interview.fb;

import java.util.List;
import algorithm.base.Interval;

public class LeetCode435NonOverlappingIntervals {
  
  public int eraseOverlapIntervals(List<Interval> intervals) {
    // write your code here
    if (intervals == null || intervals.size() == 0) {
      return 0;
    }
    
    int iLen = intervals.size();
    intervals.sort((a, b) -> {int diff = a.start - b.start;
    return diff == 0 ? b.end - a.end : diff;
    });
    
    int count = 0;
    Interval prev = intervals.get(0);
    for (int i = 1; i < iLen; i++) {
      Interval cursor = intervals.get(i);
      if (prev.end >= cursor.end) {
        count++;
        prev = cursor;
      } else if (prev.end > cursor.start) {
        count++;
      } else {
        prev = cursor;
      }
    }
    
    return count;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
