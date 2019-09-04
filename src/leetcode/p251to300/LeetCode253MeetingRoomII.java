package leetcode.p251to300;

import basicclass.Interval;

import java.util.Arrays;

/**
 * LeetCode 253. Meeting Rooms II
 * Medium
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */
@SuppressWarnings("unused")
public class LeetCode253MeetingRoomII {

    private interface Method {
        int minMeetingRooms(Interval[] intervals);
    }

    private static class SpiteAndSort implements Method {

        private static class Point implements Comparable<Point> {
            int time;
            int flag;

            private Point(int time, int flag) {
                this.time = time;
                this.flag = flag;
            }

            @Override
            public int compareTo(Point o) {
                return time != o.time ? time - o.time : flag - o.flag;
            }
        }

        @Override
        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }
            int res = 0;
            int count = 0;
            Point[] points = new Point[intervals.length * 2];
            for (int i = 0; i < points.length; i += 2) {
                Interval interval = intervals[i / 2];
                points[i] = new Point(interval.start, 1);
                points[i + 1] = new Point(interval.end, -1);
            }
            Arrays.sort(points);

            for (Point point : points) {
                count += point.flag;
                res = Math.max(res, count);
            }
            return res;
        }
    }
}
