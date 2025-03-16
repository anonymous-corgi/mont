package com.anonymouscorgi.karakoram.kb0900

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium

@Medium
internal interface LeetCode930BinarySubarraysWithSum {

    fun numSubarraysWithSum(nums: IntArray, goal: Int): Int

    @Accepted
    object SUM_MAP : LeetCode930BinarySubarraysWithSum {

        override fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
            var sum = 0
            var count = 0
            val sumFreq = mutableMapOf(0 to 1)
            for (num in nums) {
                sum += num
                count += sumFreq.getOrDefault(sum - goal, 0)
                sumFreq[sum] = sumFreq.getOrDefault(sum, 0) + 1
            }
            return count
        }
    }
}