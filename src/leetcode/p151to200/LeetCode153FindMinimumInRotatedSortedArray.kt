package leetcode.p151to200

class LeetCode153FindMinimumInRotatedSortedArray {

    fun findMin(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        var start = 0
        var end = nums.size - 1
        val reference = nums[end]
        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                nums[mid] <= reference -> end = mid
                else -> start = mid + 1
            }
        }
        return nums[end]
    }
}