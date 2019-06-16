package leetcode.p201to250

import java.util.*

class LeetCode210CourseScheduleII {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>?): IntArray {
        if (numCourses == 0 || prerequisites == null) {
            return IntArray(0)
        }
        var index = 0
        val res = IntArray(numCourses)
        val inDegrees = IntArray(numCourses)
        val successors = HashMap<Int, MutableList<Int>>()
        val taskList = ArrayDeque<Int>()
        for (prerequisite in prerequisites) {
            inDegrees[prerequisite[0]]++
            successors.getOrPut(prerequisite[1]) { mutableListOf() }.add(prerequisite[0])
        }
        for (i in 0 until numCourses) {
            if (inDegrees[i] == 0) {
                taskList.offer(i)
            }
        }
        while (!taskList.isEmpty()) {
            val cursor = taskList.poll()
            res[index++] = cursor
            for (successor in successors[cursor] ?: emptyList<Int>()) {
                if (--inDegrees[successor] == 0) {
                    taskList.offer(successor)
                }
            }
        }
        return if (index == numCourses) res else IntArray(0)
    }
}
