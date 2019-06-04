package leetcode.p001to050

class LeetCode034FindFirstandLastPositionofElementinSortedArray {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        var start = 0
        var end = nums.size - 1
        val res = intArrayOf(-1, -1)
        // Find first
        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                nums[mid] >= target -> end = mid
                else -> start = mid + 1
            }
        }
        if (nums[start] != target) return res
        res[0] = start

        end = nums.size - 1
        // Find last
        while (start < end) {
            val mid = start + (end - start + 1) / 2
            when {
                nums[mid] <= target -> start = mid
                else -> end = mid - 1
            }
        }
        res[1] = end
        return res
    }
}