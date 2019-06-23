package leetcode.p251to300

import basicclass.Interval

class LeetCode252MeetingRoom {

    fun canAttendMeetings(intervals: List<Interval>?): Boolean {
        if (intervals.isNullOrEmpty()) {
            return true
        }
        val sortedIntervals = intervals.sortedWith(Comparator { a, b ->
            if (a.start == b.start) a.end - b.end else a.start - b.start
        })

        var end = 0
        for (cursor in sortedIntervals) {
            if (cursor.start < end) {
                return false
            }
            end = cursor.end
        }
        return true
    }
}
