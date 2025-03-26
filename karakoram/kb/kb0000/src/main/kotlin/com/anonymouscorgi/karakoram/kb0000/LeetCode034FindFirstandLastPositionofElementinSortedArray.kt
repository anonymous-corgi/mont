package com.anonymouscorgi.karakoram.kb0000

internal interface LeetCode034FindFirstandLastPositionofElementinSortedArray {

  fun searchRange(nums: IntArray, target: Int): IntArray

  object METHOD : LeetCode034FindFirstandLastPositionofElementinSortedArray {

    override fun searchRange(nums: IntArray, target: Int): IntArray {
      if (nums.isEmpty()) return intArrayOf(-1, -1)

      var leftIndex = 0
      var rightIndex = nums.lastIndex
      while (leftIndex < rightIndex) {
        val midIndex = leftIndex + (rightIndex - leftIndex) / 2;
        when {
          nums[midIndex] < target -> leftIndex = midIndex + 1
          else -> rightIndex = midIndex
        }
      }
      val targetStart = rightIndex
      if (nums[targetStart] != target) {
        return intArrayOf(-1, -1)
      }

      leftIndex = maxOf(targetStart, 0)
      rightIndex = nums.lastIndex
      while (leftIndex < rightIndex) {
        val midIndex = leftIndex + (rightIndex - leftIndex + 1) / 2;
        when {
          nums[midIndex] > target -> rightIndex = midIndex - 1
          else -> leftIndex = midIndex
        }
      }
      val targetEnd = leftIndex

      return intArrayOf(targetStart, targetEnd)
    }
  }

  object Method2 : LeetCode034FindFirstandLastPositionofElementinSortedArray {
    override fun searchRange(nums: IntArray, target: Int): IntArray {
      if (nums.isEmpty()) {
        return intArrayOf(-1, -1)
      }

      val res = intArrayOf(-1, -1)
      var start = 0
      var end = nums.size - 1
      while (start < end) {
        val mid = start + (end - start) / 2
        if (nums[mid] < target) {
          start = mid + 1
        } else {
          end = mid
        }
      }

      if (nums[end] != target) {
        return res
      }
      res[0] = end

      end = nums.size - 1
      while (start < end) {
        val mid = start + (end - start + 1) / 2
        if (nums[mid] > target) {
          end = mid - 1
        } else {
          start = mid
        }
      }
      res[1] = start
      return res
    }
  }
}