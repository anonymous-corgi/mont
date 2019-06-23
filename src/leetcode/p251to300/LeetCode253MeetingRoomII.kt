package leetcode.p251to300

import basicclass.Interval
import kotlin.math.max

class LeetCode253MeetingRoomII {

    internal inner class Point(val time: Int, val flag: Int) : Comparable<Point> {

        override fun compareTo(o: Point): Int {
            return if (time != o.time) time - o.time else flag - o.flag
        }
    }

    fun minMeetingRooms(intervals: List<Interval>?): Int {
        if (intervals.isNullOrEmpty()) {
            return 0
        }
        val sortedTime = MutableList(intervals.size shl 1) {
            Point(intervals[it / 2].start, if (it % 2 == 0) 1 else -1)
        }
        sortedTime.sort()
        var count = 0
        var max = 0
        for (point in sortedTime) {
            count += point.flag
            max = max(max, count)
        }
        return max
    }
}
