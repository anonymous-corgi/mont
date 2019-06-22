package leetcode.p101to150

import java.util.*

class LeetCode126WordLadderII {

    interface Method {
        fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<List<String>>
    }

    internal class Normal_Method : Method {

        override fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
            if (beginWord == endWord) {
                return listOf(listOf(beginWord))
            }
            var hasResult = false
            val elements = LinkedHashSet<String>().apply {
                addAll(wordList)
                remove(beginWord)
            }
            if (!elements.contains(endWord)) return emptyList()
            val taskQueue = ArrayDeque<List<String>>().apply { offer(listOf(beginWord)) }
            val results = mutableListOf<List<String>>()
            while (taskQueue.isNotEmpty()) {
                val checkedSet = mutableSetOf<String>()
                for (i in taskQueue.indices) {
                    val list = taskQueue.poll()
                    val lastWord: String = list.last()
                    for (nextWord in elements) {
                        if (isNextWord(lastWord, nextWord)) {
                            if (nextWord == endWord) {
                                results.add(list + nextWord)
                                hasResult = true
                            } else {
                                taskQueue.offer((list + nextWord))
                            }
                            checkedSet.add(nextWord)
                        }
                    }
                }
                elements.removeAll(checkedSet)
                if (hasResult) {
                    break
                }
            }
            return results
        }

        private fun isNextWord(beginWord: String, endWord: String): Boolean {
            var hasDiff = false
            for (index in beginWord.indices) {
                if (beginWord[index] != endWord[index]) {
                    if (hasDiff) return false
                    hasDiff = true
                }
            }
            return hasDiff
        }
    }
}