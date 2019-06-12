package jiuzhang.c4.levelordertraversal

import java.util.*

class LeetCode261GraphValidTree {

    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        // write your code here
        if (n == 0) {
            return false
        }
        val graph = initializeGraph(n, edges)
        val taskList = ArrayDeque<Int>()
        val checkList = mutableSetOf<Int>()

        taskList.offer(0)
        checkList.add(0)
        while (!taskList.isEmpty()) {
            val node = taskList.poll()
            for (eachNeighbor in graph[node]!!) {
                if (checkList.contains(eachNeighbor)) {
                    continue
                }
                checkList.add(eachNeighbor)
                taskList.offer(eachNeighbor)
            }
        }

        return checkList.size == n
    }

    private fun initializeGraph(num: Int, edges: Array<IntArray>): Map<Int, Set<Int>> {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0 until num) {
            graph[i] = mutableSetOf()
        }

        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            graph[u]?.add(v)
            graph[v]?.add(u)
        }
        return graph
    }
}
