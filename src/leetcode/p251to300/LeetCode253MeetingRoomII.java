package leetcode.p251to300;

import java.util.Arrays;
import java.util.List;
import basicclass.Interval;

public class LeetCode253MeetingRoomII {
  
  class Point implements Comparable<Point> {
    int time;
    int flag;
    Point(int time, int flag) {
      this.time = time;
      this.flag = flag;
    }

    @Override
    public int compareTo(Point o) {
      return time != o.time ? time - o.time : flag - o.flag;
    }
  }
  
  public int minMeetingRooms(List<Interval> intervals) {
    if (intervals == null || intervals.size() == 0) {
      return 0;
    }
    int index = 0;
    Point[] list = new Point[intervals.size()<< 1];
    for (Interval it : intervals) {
      list[index++] = new Point(it.start, 1);
      list[index++] = new Point(it.end, -1);
    }
    Arrays.sort(list);
    int count = 0;
    int max = 0;
    for (Point p : list) {
      count += p.flag;
      max = Math.max(max, count);
    }
    return max;
  }

}
