package leetcode.p401to450

import junit.framework.Assert.assertFalse
import org.junit.Test

class LeetCode444SequenceReconstruction {

    // Actually, you need to check two things:
    // 1. seqs contains all the elements in org, but doesn't contain number not in org
    // 2. The number order of seqs is consistent with org
    internal interface SequenceReconstruction {
        fun sequenceReconstruction(org: IntArray, seqs: Array<IntArray>): Boolean
    }

    internal class Verification_Method : SequenceReconstruction {

        override fun sequenceReconstruction(org: IntArray, seqs: Array<IntArray>): Boolean {
            if (org.isEmpty()) {
                return true
            }
            var seqsHasNumber = false
            val pos = IntArray(org.size + 1)
            val checked = BooleanArray(org.size + 1)
            var checkedNum = org.size - 1
            for (i in org.indices) {
                pos[org[i]] = i
            }
            for (seq in seqs) {
                for (i in seq.indices) {
                    if (seq[i] < 1 || seq[i] > org.size) {
                        return false
                    }
                    seqsHasNumber = true
                    if (i == 0) {
                        continue
                    }
                    if (pos[seq[i - 1]] >= pos[seq[i]]) {
                        return false
                    }
                    if (!checked[pos[seq[i]]] && pos[seq[i - 1]] + 1 == pos[seq[i]]) {
                        checked[pos[seq[i]]] = true
                        checkedNum--
                    }
                }
            }
            return seqsHasNumber && checkedNum == 0
        }
    }

    internal class BFS_Method : SequenceReconstruction {

        override fun sequenceReconstruction(org: IntArray, seqs: Array<IntArray>): Boolean {
            if (org.isEmpty()) {
                return true
            }

            val inDegrees = IntArray(org.size + 1)
            inDegrees[0] = Integer.MAX_VALUE
            val successors = mutableMapOf<Int, MutableSet<Int>>()
            val taskList = mutableListOf<Int>()

            for (seq in seqs) {
                // seq might contains number that isn't in org, we need to verify it.
                for (num in seq) {
                    if (num < 1 || num > org.size) {
                        return false
                    }
                    successors.putIfAbsent(num, HashSet())
                }
                for (j in seq.size - 2 downTo 0) {
                    if (successors[seq[j]]!!.add(seq[j + 1])) {
                        inDegrees[seq[j + 1]]++
                    }
                }
            }

            if (successors.size != org.size) {
                return false
            }

            for (i in 1..org.size) {
                if (inDegrees[i] == 0) {
                    taskList.add(i)
                }
            }

            var index = 0
            while (taskList.size == 1) {
                val cursor = taskList.removeAt(0)
                if (cursor != org[index++]) {
                    return false
                }
                for (successor in successors[cursor] ?: emptySet<Int>()) {
                    if (--inDegrees[successor] == 0) {
                        taskList.add(successor)
                    }
                }
            }
            return index == org.size
        }
    }

    @Test
    fun testCase1() {
        val org = intArrayOf(1)
        val seqs = arrayOf<IntArray>()
        val method = getSolution()
        assertFalse(method.sequenceReconstruction(org, seqs))
    }

    @Test
    fun testCase2() {
        val org = intArrayOf(1, 2, 3)
        val seqs = arrayOf(intArrayOf(3, 2), intArrayOf(2, 1))
        val method = getSolution()
        assertFalse(method.sequenceReconstruction(org, seqs))
    }

    companion object {

        private fun getSolution(): SequenceReconstruction = BFS_Method()

        @JvmStatic
        fun main(args: Array<String>) {
            val org = intArrayOf(1)
            val seqs = arrayOf<IntArray>()
            //        int[] org = {1, 2, 3};
            //        int[][] seqs = {{1, 2}, {1, 3}, {2, 3}};
            val method = getSolution()
            method.sequenceReconstruction(org, seqs)
        }
    }
}
