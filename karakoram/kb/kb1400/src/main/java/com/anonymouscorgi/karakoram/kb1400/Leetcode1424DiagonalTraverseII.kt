package com.anonymouscorgi.karakoram.kb1400

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium
import kotlin.math.max

@Medium
internal interface Leetcode1424DiagonalTraverseII {

  fun findDiagonalOrder(nums: List<List<Int>>): IntArray

  companion object {

    @Accepted
    val METHOD = object : Leetcode1424DiagonalTraverseII {
      override fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
        var maxLength = 0
        for (index in nums.indices) {
          maxLength = max(maxLength, index + nums[index].size)
        }
        val posLists = Array<MutableList<Int>>(maxLength) { mutableListOf() }

        for (rowIndex in nums.indices) {
          for (colIndex in nums[rowIndex].indices) {
            posLists[rowIndex + colIndex].add(nums[rowIndex][colIndex])
          }
        }
        return posLists.flatMap { it.reversed() }.toIntArray()
      }
    }

    @Accepted
    val METHOD_GPT_1 = object : Leetcode1424DiagonalTraverseII {
      override fun findDiagonalOrder(nums: List<List<Int>>): IntArray =
        nums
          // 1) Tag each element with its diagonal index = row + col
          .flatMapIndexed { row, rowList ->
            rowList.mapIndexed { col, value ->
              (row + col) to value
            }
          }
          // 2) Group values by diagonal index
          .groupBy(
            keySelector = { it.first },
            valueTransform = { it.second }
          )
          // 3) Ensure diagonals are processed in increasing index order
          .toSortedMap()
          // 4) For each diagonal, append values in reverse order
          .flatMap { (_, values) ->
            values.asReversed()
          }
          // 5) Convert the result to IntArray
          .toIntArray()
    }

    @Accepted
    val METHOD_GPT_2 = object : Leetcode1424DiagonalTraverseII {
      override fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
        // 1) Flatten with (diagonal index) pairs: (i+j) → value
        val paired: List<Pair<Int, Int>> = nums.flatMapIndexed { i, row ->
          row.mapIndexed { j, value ->
            i + j to value
          }
        }

        // 2) Group by diagonal index, which preserves the order of keys as they first appear
        val diagonals: Map<Int, List<Int>> = paired
          .groupBy(
            keySelector   = { it.first },
            valueTransform = { it.second }
          )

        // 3) For each bucket in ascending order of key, reverse it and collect
        return diagonals
          .toSortedMap()            // ensure keys are in increasing order
          .values
          .asSequence()
          .flatMap { it.asReversed() }
          .toList()
          .toIntArray()
      }
    }
  }
}