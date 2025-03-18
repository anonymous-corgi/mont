package com.anonymouscorgi.karakoram.kb0400

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium

/**
 * LeetCode 416. Partition Equal Subset Sum
 *
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 */
@Medium
internal interface LeetCode416PartitionEqualSubsetSum {

    fun canPartition(nums: IntArray): Boolean

    @Accepted
    object METHOD : LeetCode416PartitionEqualSubsetSum {

        override fun canPartition(nums: IntArray): Boolean {
            val sum = nums.sum()
            if (sum % 2 == 1) {
                return false
            }
            val target = sum / 2
            val possibleSum = BooleanArray(target + 1)
            possibleSum[0] = true
            for (num in nums) {
                for (index in target downTo 1) {
                    if (index < num) break
                    possibleSum[index] = possibleSum[index] || possibleSum[index - num]
                }
            }
            return possibleSum[target]
        }
    }
}