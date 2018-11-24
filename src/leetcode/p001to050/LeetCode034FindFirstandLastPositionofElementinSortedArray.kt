package leetcode.p001to050

class LeetCode034FindFirstandLastPositionofElementinSortedArray {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.size == 0) return intArrayOf(-1, -1)
        var start = 0
        var end = nums.size - 1
        var res = intArrayOf(-1, -1)
        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                nums[mid] < target -> start = mid + 1
                else -> end = mid
            }
        }
        if (nums[end] != target) return res
        res[0] = end

        end = nums.size - 1
        while (start < end) {
            val mid = start + (end - start + 1) / 2
            when {
                nums[mid] > target -> end = mid - 1
                else -> start = mid
            }
        }
        res[1] = start
        return res
    }
}