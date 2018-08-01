package leetcode.p251to300;

import java.util.Collections;
import java.util.List;
import basicclass.Interval;

public class LeetCode252MeetingRoom {
  
  public boolean canAttendMeetings(List<Interval> intervals) {
    // Write your code here
    if (intervals == null || intervals.size() == 0) {
      return true;
    }
    Collections.sort(intervals, (a, b) -> {return a.start == b.start 
        ? a.end - b.end : a.start - b.start;});
    int end = 0;
    for (int i = 0, len = intervals.size(); i < len; i++) {
      Interval cursor = intervals.get(i);
      if (cursor.start < end) {
        return false;
      }
      end = cursor.end;
    }
    return true;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
