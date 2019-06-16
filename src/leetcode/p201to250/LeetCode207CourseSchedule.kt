package leetcode.p201to250

class LeetCode207CourseSchedule {

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        if (numCourses < 2) {
            return numCourses == 1
        }
        val inDegrees = IntArray(numCourses)
        val successors = mutableMapOf<Int, MutableList<Int>>()
        var visitedNum = 0
        val taskList = mutableListOf<Int>()
        for (req in prerequisites) {
            inDegrees[req[0]]++
            successors.getOrPut(req[1]) { mutableListOf() }.add(req[0])
        }
        for (i in 0 until numCourses) {
            if (inDegrees[i] == 0) {
                taskList.add(i)
                visitedNum++
            }
        }
        while (taskList.isNotEmpty()) {
            val cursor = taskList.removeAt(taskList.lastIndex)
            for (successor in successors[cursor] ?: emptyList<Int>()) {
                if (--inDegrees[successor] == 0) {
                    taskList.add(successor)
                    visitedNum++
                }
            }
        }
        return visitedNum == numCourses
    }
}