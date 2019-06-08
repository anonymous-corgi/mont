package leetcode.p001to050

class LeetCode033SearchInRotatedSortedArray {

    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var start = 0
        var end = nums.size - 1
        val reference = nums[end]
        while (start < end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) return mid
            if (nums[mid] > target) {
                if (nums[mid] > reference && target <= reference) start = mid + 1
                else end = mid - 1
            } else {
                if (target > reference && nums[mid] <= reference) end = mid - 1
                else start = mid + 1
            }
        }
        return if (nums[start] == target) start else -1
    }
}