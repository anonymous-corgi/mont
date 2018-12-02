package leetcode.p101to150

import org.junit.Test
import kotlin.test.assertEquals

class LeetCode135Candy {

    fun candy(ratings: IntArray?): Int {
        if (ratings == null || ratings.isEmpty()) {
            return 0
        }
        var res = 0
        val dp = IntArray(ratings.size)
        dp[0] = 1
        for (i in 1 until ratings.size) {
            dp[i] = if (ratings[i] > ratings[i - 1]) dp[i - 1] + 1 else 1
        }
        for (i in ratings.size - 1 downTo 1) {
            if (ratings[i - 1] > ratings[i]) {
                dp[i - 1] = Math.max(dp[i] + 1, dp[i - 1])
            }
        }
        for (i in 0 until ratings.size) {
            res += dp[i]
        }
        return res
    }

    @Test
    fun testCandy1() {
        val method = LeetCode135Candy()
        val ratings = intArrayOf(1, 2, 3, 3, 3, 1)
        val res = 10
        assertEquals(res, method.candy(ratings))
    }

    @Test
    fun testCandy2() {
        val method = LeetCode135Candy()
        val ratings = intArrayOf(1, 0, 2)
        val res = 5
        assertEquals(res, method.candy(ratings))
    }

    @Test
    fun testCandy3() {
        val method = LeetCode135Candy()
        val ratings = intArrayOf(1, 2, 2)
        val res = 4
        assertEquals(res, method.candy(ratings))
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val one = LeetCode135Candy()
            val ratings = intArrayOf(1, 2, 3, 3, 3, 1)
            println(one.candy(ratings))

        }
    }

}
