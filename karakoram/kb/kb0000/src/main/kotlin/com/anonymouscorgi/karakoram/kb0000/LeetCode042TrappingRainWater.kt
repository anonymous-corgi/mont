package com.anonymouscorgi.karakoram.kb0000

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Hard

/**
 * LeetCode 42. Trapping Rain Water
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 *
 *     n == height.length
 *     1 <= n <= 2 * 104
 *     0 <= height[i] <= 105
 *
 *
 */
@Hard
internal interface LeetCode042TrappingRainWater {

  fun trap(height: IntArray): Int

  @Accepted
  object METHOD : LeetCode042TrappingRainWater {

    override fun trap(height: IntArray): Int {
      if (height.isEmpty()) {
        return 0
      }

      var left = 0
      var right = height.size - 1
      var lHeight = height[left]
      var rHeight = height[right]
      var res = 0

      while (left < right) {
        lHeight = maxOf(lHeight, height[left])
        rHeight = maxOf(rHeight, height[right])

        res += if (height[left] < height[right]) {
          lHeight - height[left++]
        } else {
          rHeight - height[right--]
        }
      }
      return res
    }
  }
}