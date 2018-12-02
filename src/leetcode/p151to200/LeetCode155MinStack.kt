package leetcode.p151to200

import java.util.Stack

class LeetCode155MinStack {

    interface Method {
        fun push(x: Int)
        fun pop()
        fun top(): Int
        fun getMin(): Int
    }

    class OneStask_Method : Method {

        private var min: Long = 0
        private val stack: Stack<Long> = Stack()

        override fun push(x: Int) {
            val num = x.toLong()
            if (stack.isEmpty()) {
                // !!!!!! push(0l)
                stack.push(0L)
                min = num
            } else {
                stack.push(num - min)
                if (num < min) {
                    min = num
                }
            }
        }

        override fun pop() {
            if (stack.isEmpty()) {
                return
            }
            val num = stack.pop()
            if (num < 0) {
                min -= num
            }
        }

        override fun top(): Int {
            val num = stack.peek()
            return if (num < 0) {
                min.toInt()
            } else {
                (num + min).toInt()
            }
        }

        override fun getMin(): Int {
            return min.toInt()
        }
    }

    class TwoStack_Method : Method {

        private val minStack = Stack<Int>()
        private val norStack = Stack<Int>()

        override fun push(x: Int) {
            if (norStack.isEmpty()) {
                minStack.push(x)
            } else {
                minStack.push(Math.min(x, minStack.peek()))
            }
            norStack.push(x)
        }

        override fun pop() {
            minStack.pop()
            norStack.pop()
        }

        override fun top(): Int {
            return norStack.peek()
        }

        override fun getMin(): Int {
            return minStack.peek()
        }
    }
}
