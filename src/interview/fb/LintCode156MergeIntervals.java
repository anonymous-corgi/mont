package interview.fb;

import java.util.ArrayList;
import java.util.List;
import basicclass.Interval;

public class LintCode156MergeIntervals {
  
  public List<Interval> merge(List<Interval> intervals) {
    // write your code here
    List<Interval> result = new ArrayList<>();
    if (intervals == null || intervals.size() == 0) {
      return result;
    }
    
    // intervals.sort(new Comparator<Interval>(){
    //   public int compare(Interval a, Interval b) {
    //       return a.start - b.start;
    //   } 
    // });
    intervals.sort((a, b) -> a.start - b.start);
    Interval head = intervals.get(0);
    for (int i = 1, len = intervals.size(); i < len; i++) {
      head = helper(head, intervals.get(i), result);
    }
    result.add(head);
    
    return result;
  }
  
  private Interval helper(Interval head, Interval next, List<Interval> result) {
    if (head.end < next.start) {
      result.add(head);
      return next;
    }
    head.end = Math.max(head.end, next.end);
    return head;
  }

}
