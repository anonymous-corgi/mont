package leetcode.p101to150

import java.util.*

class LeetCode127WordLadder {

    interface Method {
        fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int
    }

    internal class Normal_Method : Method {

        override fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
            if (beginWord == endWord) return 1
            if (!wordList.contains(endWord)) return 0
            var layer = 1
            val taskQueue = ArrayDeque<String>().apply { add(beginWord) }
            val elements = mutableSetOf<String>().apply { addAll(wordList) }
            while (taskQueue.isNotEmpty()) {
                layer++
                for (i in taskQueue.indices) {
                    val curWord = taskQueue.poll()
                    val iterator = elements.iterator()
                    while (iterator.hasNext()) {
                        val nextWord = iterator.next()
                        if (isNextWord(curWord, nextWord)) {
                            if (nextWord == endWord) return layer
                            iterator.remove()
                            taskQueue.offer(nextWord)
                        }
                    }
                }
            }
            return 0
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