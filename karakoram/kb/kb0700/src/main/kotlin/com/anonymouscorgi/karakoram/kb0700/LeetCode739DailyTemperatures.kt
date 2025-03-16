package com.anonymouscorgi.karakoram.kb0700

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium

@Medium
internal interface LeetCode739DailyTemperatures {

    fun dailyTemperatures(temperatures: IntArray): IntArray

    @Accepted
    object METHOD : LeetCode739DailyTemperatures {

        override fun dailyTemperatures(temperatures: IntArray): IntArray {
            val result = IntArray(temperatures.size)
            val stack = ArrayDeque<Int>()

            for (dayNum in temperatures.indices) {
                while (!stack.isEmpty() && temperatures[stack.last()] < temperatures[dayNum]) {
                    val index = stack.removeLast()
                    result[index] = dayNum - index
                }
                stack.addLast(dayNum)
            }
            return result
        }
    }
}