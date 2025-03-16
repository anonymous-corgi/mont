package com.anonymouscorgi.karakoram.kb0150

import com.anonymouscorgi.karakoram.annotation.Medium

/**
 * LeetCode 153. Find Minimum in Rotated Sorted Array
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 *     [4,5,6,7,0,1,2] if it was rotated 4 times.
 *     [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 */
@Medium
internal interface LeetCode153FindMinimumInRotatedSortedArray {

    fun findMin(nums: IntArray): Int;

    object METHOD : LeetCode153FindMinimumInRotatedSortedArray {

        override fun findMin(nums: IntArray): Int {
            var left = 0
            var right = nums.lastIndex
            val pivot = nums[right]

            while (left < right) {
                val mid = left + (right - left) / 2
                if (nums[mid] <= pivot) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }
            return nums[right]
        }
    }
}