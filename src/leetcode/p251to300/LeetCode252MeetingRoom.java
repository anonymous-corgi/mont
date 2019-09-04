package leetcode.p251to300;

import basicclass.Interval;

import java.util.Arrays;

/**
 * LeetCode 252. Meeting Room
 * Easy
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 */
@SuppressWarnings("unused")
public class LeetCode252MeetingRoom {

    private interface Method {
        boolean canAttendMeetings(Interval[] intervals);
    }

    private static class SplitAndSort implements Method {

        @Override
        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) {
                return true;
            }
            Arrays.sort(intervals, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
            int lastEnd = 0;
            for (Interval interval : intervals) {
                if (interval.start < lastEnd) {
                    return false;
                }
                lastEnd = interval.end;
            }
            return true;
        }
    }
}
