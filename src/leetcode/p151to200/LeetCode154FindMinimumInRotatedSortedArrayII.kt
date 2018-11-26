package leetcode.p151to200

class LeetCode154FindMinimumInRotatedSortedArrayII {

    fun findMin(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        var start = 0
        var end = nums.size - 1
        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                nums[mid] > nums[end] -> start = mid + 1
                nums[mid] < nums[end] -> end = mid
                else -> end--
            }
        }
        return nums[end]
    }
}