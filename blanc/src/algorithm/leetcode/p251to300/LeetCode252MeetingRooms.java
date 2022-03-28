package algorithm.leetcode.p251to300;

import java.util.Arrays;

/**
 * 252. Meeting Rooms
 * Easy
 *
 * Given an array of meeting time intervals consisting of
 * start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: true
 */
@SuppressWarnings("unused")
public class LeetCode252MeetingRooms {

    private interface Method {
        boolean canAttendMeetings(int[][] intervals);
    }

    private static final class Normal implements Method {

        public boolean canAttendMeetings(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return true;
            }
            Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            int lastEnd = 0;
            for (int[] interval : intervals) {
                if (interval[0] < lastEnd) {
                    return false;
                }
                lastEnd = interval[1];
            }
            return true;
        }
    }
}
