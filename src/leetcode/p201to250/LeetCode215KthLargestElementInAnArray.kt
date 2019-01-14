package leetcode.p201to250

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.Serializable

@RunWith(Parameterized::class)
class LeetCode215KthLargestElementInAnArray(val res: Int, val nums: IntArray?, val k: Int) {

    fun findKthLargest(): Int {
        return findKthLargest(nums, k)
    }

    fun findKthLargest(nums: IntArray?, k: Int): Int {
        return if (nums == null || nums.size < k) {
            -1
        } else findKth(nums, 0, nums.size - 1, k)
    }

    private fun findKth(nums: IntArray, start: Int, end: Int, k: Int): Int {
        var left = start - 1
        var cursor = start
        val pivot = nums[end]
        while (cursor <= end) {
            if (nums[cursor] >= pivot) {
                swap(nums, ++left, cursor)
            }
            cursor++
        }

        return when {
            k - 1 < left -> findKth(nums, start, left - 1, k)
            k - 1 > left -> findKth(nums, left + 1, end, k)
            else -> nums[k - 1]
        }
    }

    private fun swap(nums: IntArray, a: Int, b: Int) {
        val temp = nums[a]
        nums[a] = nums[b]
        nums[b] = temp
    }

    @Test
    fun testFindKthLargest() {
        assertEquals(res, findKthLargest(nums, k))
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Serializable>> {
            return listOf(
                    arrayOf(5, intArrayOf(3, 2, 1, 5, 6, 4), 2)
            )
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val actRes = LeetCode215KthLargestElementInAnArray(5, intArrayOf(3, 2, 1, 5, 6, 4), 2).findKthLargest()
            println(actRes)
        }
    }

}
