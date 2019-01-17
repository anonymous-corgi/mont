package leetcode.p201to250

class LeetCode268MissingNumber {

    interface Method {
        fun missingNumber(nums: IntArray): Int
    }

    class XOR_Method : Method {

        override fun missingNumber(nums: IntArray): Int {
            var res = nums.size
            for (i in nums.indices) {
                res = res.xor(i)
                res = res.xor(nums[i])
            }
            return res
        }
    }

    class Sum_Method : Method {

        override fun missingNumber(nums: IntArray): Int {
            var res = nums.size
            for (i in nums.indices) {
                res += i
                res -= nums[i]
            }
            return res
        }
    }

    private fun getInstance() : Method {
        return XOR_Method()
    }

    fun missingNumber(nums: IntArray): Int {
        return getInstance().missingNumber(nums)
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val instance = LeetCode268MissingNumber()
            val nums = intArrayOf(3, 0, 1)
            println(instance.missingNumber(nums))
        }
    }
}