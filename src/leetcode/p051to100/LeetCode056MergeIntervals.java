package leetcode.p051to100;

import java.util.ArrayList;
import java.util.List;
import basicclass.Interval;

public class LeetCode056MergeIntervals {
  //Method 1:
  public List<Interval> merge(List<Interval> intervals) {
    List<Interval> res = new ArrayList<>();
    if (intervals == null || intervals.size() == 0) {
      return res;
    }
    intervals.sort((a, b) -> {return a.start == b.start 
        ? a.end - b.end : a.start - b.start;});
    Interval last = intervals.get(0);
    for (int i = 1, len = intervals.size(); i < len; i++) {
      last = helper(last, intervals.get(i), res);
    }
    res.add(last);
    return res;
  }
  
  private Interval helper(Interval last, Interval neo, List<Interval> res) {
    if (last.end < neo.start) {
      res.add(last);
      return neo;
    }
    last.end = Math.max(last.end, neo.end);
    return last;
  }
	
  //Method 2:
//  public List<Interval> merge(List<Interval> intervals) {
//    List<Interval> res = new ArrayList<Interval>();
//    if (intervals == null || intervals.size() == 0) {
//      return res;
//    }
//    intervals.sort((a, b) -> {return a.start == b.start 
//        ? a.end - b.end : a.start - b.start;});
//    int neoCursor = 0;
//    res.add(intervals.get(0));
//    for (int preCursor = 1; preCursor < intervals.size(); preCursor++) {
//      Interval neo = res.get(neoCursor);
//      Interval pre = intervals.get(preCursor);
//      if (neo.end < pre.start) {
//        res.add(pre);
//        neoCursor++;
//      } else {
//        mergeTwoIntervals(neo, pre);
//      }
//    }
//    return res;
//  }
//  
//  private Interval mergeTwoIntervals(Interval a, Interval b) {
//    if (a.end < b.end) {
//      a.end = b.end;
//    }
//    return a;
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[][] arrs = {{1,3},{2,6},{8,10},{15,18}};
    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < arrs.length; i++) {
      list.add(new Interval(arrs[i][0], arrs[i][1]));
    }
    LeetCode056MergeIntervals one = new LeetCode056MergeIntervals();
    one.merge(list);
  }
  
}