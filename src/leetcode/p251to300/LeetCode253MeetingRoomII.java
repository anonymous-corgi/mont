package leetcode.p251to300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import basicclass.Interval;

public class LeetCode253MeetingRoomII {
  
  class Point {
    int time;
    int flag;
    Point(int time, int flag) {
      this.time = time;
      this.flag = flag;
    }
  }
  
  public int minMeetingRooms(List<Interval> intervals) {
    if (intervals == null || intervals.size() == 0) {
      return 0;
    }
    int len = intervals.size();
    List<Point> list = new ArrayList<>();
    for (int i = 0; i < len ;i++) {
      Interval cursor = intervals.get(i);
      list.add(new Point(cursor.start, 1));
      list.add(new Point(cursor.end, 0));
    }
    Collections.sort(list, (a, b) -> {return a.time == b.time 
        ? a.flag - b.flag : a.time - b.time;});
    int count = 0;
    int max = 0;
    for (int i = 0, iLen = list.size(); i < iLen; i++) {
      Point cursor = list.get(i);
      if (cursor.flag == 1) {
        count++;
      } else {
        count--;
      }
      max = Math.max(max, count);
    }
    return max;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode253MeetingRoomII one = new LeetCode253MeetingRoomII();
    String data = "[(65,424),(351,507),(314,807),(387,722),(19,797),(259,722),(165,221),(136,897)]";
    List<Interval> intervals = Interval.deserializeToList(data);
    System.out.println(one.minMeetingRooms(intervals));
  }

}
