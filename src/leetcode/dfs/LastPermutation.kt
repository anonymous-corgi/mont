package leetcode.dfs

import java.util.Arrays

class LastPermutation {

    /**
     * For an Ascending array, the last permutation is the reversed. (For 1,2,3,4 is 4,3,2,1)
     * For a random permutation array (1,3,2,4), we should:
     * 1. Find the range of the tail ascending array. ([2,3], tailAscendingHead = 2(index))
     * 2. Find the largest number in the tail range that is smaller than nums[tailAscendingHead - 1],
     * and swap the position.
     * 3. Reverse the entire tail range.
     */
    private fun lastPermutation(nums: IntArray?): IntArray {
        if (nums == null || nums.isEmpty()) {
            return IntArray(0)
        }
        var tailAscendingHead = nums.lastIndex
        // 1. Find the tailAscendingHead.
        while (tailAscendingHead > 0 && nums[tailAscendingHead - 1] <= nums[tailAscendingHead]) {
            tailAscendingHead--
        }

        if (tailAscendingHead != 0) {
            val pivot = nums[tailAscendingHead - 1]
            // 2. Find the largest number that is smaller than pivot (nums[smallestHead - 1])
            // and swap their positions.
            var firstSmaller = nums.lastIndex
            while (nums[firstSmaller] >= pivot) {
                firstSmaller--
            }
            swap(nums, tailAscendingHead - 1, firstSmaller)
        }
        // 3. Reverse the entire tail range.
        reverseRange(nums, tailAscendingHead, nums.lastIndex)
        return nums
    }

    private fun reverseRange(nums: IntArray, start: Int, end: Int) {
        var start = start
        var end = end
        while (start < end) {
            swap(nums, start++, end--)
        }
    }

    private fun swap(nums: IntArray, start: Int, end: Int) {
        val temp = nums[start]
        nums[start] = nums[end]
        nums[end] = temp
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 4, 2, 4, 5)
            LastPermutation().lastPermutation(nums)
            println(Arrays.toString(nums))
        }
    }

}
