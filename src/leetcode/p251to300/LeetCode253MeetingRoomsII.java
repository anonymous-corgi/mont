package leetcode.p251to300;

import java.util.Arrays;

/**
 * 253. Meeting Rooms II
 * Medium
 *
 * Given an array of meeting time intervals consisting of
 * start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
@SuppressWarnings("unused")
public class LeetCode253MeetingRoomsII {

    private interface Method {
        int minMeetingRooms(int[][] intervals);
    }

    private static class SpiteAndSort implements Method {

        private static class Point implements Comparable<Point> {
            int time;
            int flag;

            private Point(int time, int flag) {
                this.time = time;
                this.flag = flag;
            }

            public int compareTo(Point o) {
                return time != o.time ? time - o.time : flag - o.flag;
            }
        }

        public int minMeetingRooms(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }
            int res = 0;
            int count = 0;
            Point[] points = new Point[intervals.length * 2];
            for (int i = 0; i < points.length; i += 2) {
                int[] interval = intervals[i / 2];
                points[i] = new Point(interval[0], 1);
                points[i + 1] = new Point(interval[1], -1);
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
