package leetcode.p051to100

import java.util.Stack

class LeetCode084LargestRectangleInHistogram {

    fun largestRectangleArea(heights: IntArray?): Int {
        if (heights == null || heights.isEmpty()) {
            return 0
        }
        var maxArea = 0
        val stack = Stack<Int>()
        var i = 0
        while (i <= heights.size) {
            if (stack.isEmpty() || i < heights.size && heights[i] >= heights[stack.peek()]) {
                stack.push(i++)
            } else {
                val hIndex = stack.pop()
                // area = height x length
                // Because we use the height in hIndex rather than in i, (i - hIndex) isn't the length.
                // The key is stack.peek() it store the first index that is shorter than heights[hIndex]
                // Because hIndex can be contiguous or discontiguous to i,
                // the right boundary should be (i - 1).
                // The length == (i - 1) - stack.peek()
                val area = heights[hIndex] * if (stack.isEmpty()) i else i - 1 - stack.peek()
                maxArea = Math.max(maxArea, area)
            }
        }
        return maxArea
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val one = LeetCode084LargestRectangleInHistogram()
            val heights = intArrayOf(4, 6, 5, 7, 8, 3)
            one.largestRectangleArea(heights)
        }
    }

}
