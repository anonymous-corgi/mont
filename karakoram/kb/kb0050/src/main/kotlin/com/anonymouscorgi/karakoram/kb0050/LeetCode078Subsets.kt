package com.anonymouscorgi.karakoram.kb0050

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium

/**
 * LeetCode 78. Subsets
 *
 * Given an integer array nums of unique elements, return all possible
 *
 * (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
@Medium
internal interface LeetCode078Subsets {

  fun subsets(nums: IntArray): List<List<Int>>

  @Accepted
  object METHOD : LeetCode078Subsets {

    override fun subsets(nums: IntArray): List<List<Int>> {
      return mutableListOf<List<Int>>().also { dfsSubset(nums, 0, mutableListOf(), it) }
    }

    private fun dfsSubset(
      nums: IntArray,
      index: Int,
      resultBuilder: MutableList<Int>,
      results: MutableList<List<Int>>,
    ) {
      if (index > nums.lastIndex) {
        results.add(resultBuilder.toList())
      } else {
        dfsSubset(nums, index + 1, resultBuilder, results)
        dfsSubset(nums, index + 1, resultBuilder.apply { add(nums[index]) }, results)
        resultBuilder.removeLast()
      }
    }
  }
}