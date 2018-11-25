package jiuzhang.c2

class BinarySearchSumary {

    interface BinarySearch {
        fun search(nums: IntArray, target: Int): Int
    }

    // LeetCode704
    // while (start <= end)
    class Classic : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start <= end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    nums[mid] < target -> start = mid + 1
                    else -> return mid
                }
            }
            return -1
        }
    }

    class FindFirstTarget : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] < target -> start = mid + 1
                    else -> end = mid
                }
            }
            return if (nums[end] == target) end else -1
        }
    }

    class FindLastTarget : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start < end) {
                val mid = start + (end - start + 1) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    else -> start = mid
                }
            }
            return if (nums[start] == target) end else -1
        }
    }
}