package jiuzhang.c2

class BinarySearchSumary {

    interface BinarySearch {
        fun search(nums: IntArray, target: Int): Int
    }

    interface BinarySearchFindMin {
        fun findMin(nums: IntArray): Int
    }

    // LeetCode704
    // while (start <= end)
    class Classic : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
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

    // 1. For start < end, start = mid + 1, end = mid - 1,
    //    stop situation not only may be start == end also start == end + 1.
    //    But for start < end, one side is start = mid or end = mid, the find situation is start == end.
    // 2. If mid = start + 1 or mid = end - 1,
    //    (May have: INDEX OUT OF BOUNDARY problem)
    //    need to consider if mid + 1 == nums.length or mid - 1 == -1 ?
    // 3. If mid = start or mid = end,
    //    (May have: INFINITE LOOP problem) (Solution: Pick the correct end according to mid)
    //    need to consider whether next mid will still be equal to start or end?
    //    So for start < end, if mid = start + (end - start) / 2, must have start = mid + 1.
    //                        if mid = start + (end - start + 1) / 2, must have end = mid - 1;

    // while (start < end)
    class Classic2 : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    nums[mid] < target -> start = mid + 1
                    else -> return mid
                }
            }
            // Actually in the final, start must be equal to end.
            return if (nums[start] == target) start else -1
        }
    }

    class Classic3 : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            while (start < end) {
                val mid = start + (end - start + 1) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    nums[mid] < target -> start = mid + 1
                    else -> return mid
                }
            }
            // Actually in the final, start must be equal to end.
            return if (nums[end] == target) end else -1
        }
    }

    // Search First and Last
    class SearchFirstTarget : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    isQualified() -> end = mid
                    else -> start = mid + 1
                }
            }
            return if (nums[start] == target) start else -1
        }

        private fun isQualified(): Boolean {
            return true
        }
    }

    class SearchLastTarget : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            while (start < end) {
                val mid = start + (end - start + 1) / 2
                when {
                    isQualified() -> start = mid
                    else -> end = mid - 1
                }
            }
            return if (nums[end] == target) end else -1
        }

        private fun isQualified(): Boolean {
            return true
        }
    }

    // In Rotated Sorted Array

    // LeetCode 033. Search in Rotated Sorted Array
    class SearchInRotatedArray : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            val reference = nums.last()
            while (start < end) {
                val mid = start + (end - start) / 2
                if (nums[mid] == target) return mid
                if (nums[mid] > target) {
                    if (nums[mid] > reference && target <= reference) start = mid + 1
                    else end = mid - 1
                } else {
                    if (target > reference && nums[mid] <= reference) end = mid - 1
                    else start = mid + 1
                }
            }
            return if (nums[start] == target) start else -1
        }
    }

    // LeetCode 081. Search in Rotated Sorted Array II
    class SearchInRotatedArrayWithDuplicates {

        fun search(nums: IntArray, target: Int): Boolean {
            if (nums.isEmpty()) return false
            var start = 0
            var end = nums.lastIndex
            val reference = nums.last()
            while (start < end && nums[start] == reference) {
                start++
            }
            while (start < end) {
                val mid = start + (end - start) / 2
                if (nums[mid] == target) return true
                if (nums[mid] > target) {
                    if (nums[mid] > reference && target <= reference) start = mid + 1
                    else end = mid - 1
                } else {
                    if (target > reference && nums[mid] <= reference) end = mid - 1
                    else start = mid + 1
                }
            }
            return nums[start] == target
        }
    }

    // LeetCode 153. Find Minimum in Rotated Sorted Array I
    class FindMinWithoutDuplicates : BinarySearchFindMin {

        override fun findMin(nums: IntArray): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            val reference = nums.last()
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > reference -> start = mid + 1
                    else -> end = mid
                }
            }
            return nums[start]
        }
    }

    // LeetCode 154. Find Minimum in Rotated Sorted Array II
    class FindMinWithDuplicates : BinarySearchFindMin {

        override fun findMin(nums: IntArray): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.lastIndex
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > nums[end] -> start = mid + 1
                    nums[mid] < nums[end] -> end = mid
                    else -> end--
                }
            }
            return nums[start]
        }
    }

}