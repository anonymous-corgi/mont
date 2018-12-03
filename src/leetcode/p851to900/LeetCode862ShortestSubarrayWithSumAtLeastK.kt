package leetcode.p851to900

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.Serializable
import java.util.*
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class LeetCode862ShortestSubarrayWithSumAtLeastK(var res: Int,
                                                 var nums: IntArray,
                                                 var K: Int) {

    internal interface Method {
        fun shortestSubarray(nums: IntArray, K: Int): Int
    }

    // Two pointers doesn't work, because there might be negative number.
    // Under this situation, moving right pointer forward might not increase the subarray sum.
    class Standard : Method {

        override fun shortestSubarray(nums: IntArray, K: Int): Int {
            val LEN = nums.size + 1
            var res = LEN
            val prefix = IntArray(LEN)
            for (i in nums.indices) {
                prefix[i + 1] = prefix[i] + nums[i]
            }
            // que stores the tail candidates.
            val que = ArrayDeque<Int>()
            for (i in 0 until LEN) {
                while (!que.isEmpty() && prefix[i] - prefix[que.peekFirst()] >= K) {
                    res = Math.min(res, i - que.pollFirst()!!)
                }
                // The following codes maintain that for any index in que,
                // prefix[que[index]] > prefix[que[index - 1]] always satisfies.Ascending.
                while (!que.isEmpty() && prefix[que.peekLast()] >= prefix[i]) {
                    que.pollLast()
                }
                que.addLast(i)
            }
            return if (res < LEN) res else -1
        }
    }

    fun shortestSubarray(nums: IntArray, K: Int): Int {
        return testInstance.shortestSubarray(nums, K)
    }

    @Test
    fun testShortestSubarray() {
        assertEquals(res, shortestSubarray(nums, K))
    }

    companion object {

        private val testInstance: Method
            get() = Standard()

        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Serializable>> {
            return listOf(
                    arrayOf(3, intArrayOf(84, -37, 32, 40, 95), 167),
                    arrayOf(3, intArrayOf(2, -1, 2), 3)
            )
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(84, -37, 32, 40, 95)
            val K = 167
            val one = testInstance
            println(one.shortestSubarray(nums, K))
        }
    }
}
