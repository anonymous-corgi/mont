package leetcode.p251to300

import java.util.PriorityQueue
import java.util.Queue

class LeetCode295FindMedianFromDataStream {

    private val small = PriorityQueue<Long> { a, b -> (b!! - a!!).toInt() }
    private val large = PriorityQueue<Long>()

    fun addNum(num: Int) {
        large.add(num.toLong())
        small.add(large.poll())
        if (large.size < small.size)
            large.add(small.poll())
    }

    fun findMedian(): Double {
        return if (large.size != small.size)
            large.peek().toDouble()
        else
            (large.peek() + small.peek()) / 2.0
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val one = LeetCode295FindMedianFromDataStream()
            val nums = intArrayOf(1, 2, 3, 4, 5)
            for (i in nums.indices) {
                one.addNum(nums[i])
                println(one.findMedian())
            }
        }
    }
}
