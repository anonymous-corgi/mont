package leetcode.p201to250

class LeetCode201BitwiseAndOfNumbersRange {
    interface Method {
        fun rangeBitwiseAnd(m: Int, n: Int): Int
    }

    class MyMethod : Method {

        override fun rangeBitwiseAnd(m: Int, n: Int): Int {
            if (m == 0) {
                return 0
            }
            var cursor = 1 shl 30
            var res = 0
            while (cursor > n) {
                cursor = cursor.shr(1)
            }
            while (cursor.and(m) == cursor.and(n) && cursor > 0) {
                res += cursor.and(m)
                cursor = cursor.shr(1)
            }
            return res
        }
    }

    class RefMethod : Method {

        override fun rangeBitwiseAnd(m: Int, n: Int): Int {
            if (m == 0) {
                return 0
            }
            var cursor = 1
            var v1 = m
            var v2 = n
            while (v1 != v2) {
                v1 = v1.shr(1)
                v2 = v2.shr(1)
                cursor = cursor.shl(1)
            }
            return v1 * cursor
        }
    }
}