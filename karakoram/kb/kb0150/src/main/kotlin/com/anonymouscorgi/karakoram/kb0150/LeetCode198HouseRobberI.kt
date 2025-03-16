package com.anonymouscorgi.karakoram.kb0150

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium
import kotlin.math.max

/**
 * LeetCode 198. House Robber Easy
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1: Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3
 * (money = 3). Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2: Input: [2,7,9,3,1] Output: 12 Explanation: Rob house 1 (money = 2), rob house 3 (money
 * = 9) and rob house 5 (money = 1). Total amount you can rob = 2 + 9 + 1 = 12.
 */
@Medium
internal interface LeetCode198HouseRobberI {

    fun rob(nums: IntArray): Int

    object DP_Method : LeetCode198HouseRobberI {
        override fun rob(nums: IntArray): Int {
            val len = nums.size
            val dp = IntArray(len + 1)
            dp[1] = nums[0]
            for (i in 2..len) {
                dp[i] = max(dp[i - 1].toDouble(), (dp[i - 2] + nums[i - 1]).toDouble()).toInt()
            }
            return dp[len]
        }
    }

    @Accepted
    object DP_Memory_Improved_Method : LeetCode198HouseRobberI {
        override fun rob(nums: IntArray): Int {
            var minus1Max = 0
            var minus2Max = 0
            var max = 0
            for (num in nums) {
                max = max(num + minus2Max, minus1Max)
                minus2Max = minus1Max
                minus1Max = max
            }
            return max
        }
    }
}
