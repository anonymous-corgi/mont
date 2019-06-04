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

    // while (start < end)
    class Classic2 : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    nums[mid] < target -> start = mid + 1
                    else -> return mid
                }
            }
            return if (nums[start] == target) start else -1;
        }
    }

    class Classic3 : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start < end) {
                val mid = start + (end - start + 1) / 2
                when {
                    nums[mid] > target -> end = mid - 1
                    nums[mid] < target -> start = mid + 1
                    else -> return mid
                }
            }
            return if (nums[end] == target) end else -1;
        }
    }

    class SearchFirstTarget : BinarySearch {

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

    class SearchLastTarget : BinarySearch {

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

    // In Rotated Sorted Array

    // LeetCode 033. Search in Rotated Sorted Array
    class SearchInRotatedArray : BinarySearch {

        override fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            val reference = nums[end]
            while (start <= end) {
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
            return -1
        }
    }

    // LeetCode 081. Search in Rotated Sorted Array II
//    class SearchInRotatedArrayWithDuplicates : BinarySearch {
//
//        override fun search(nums: IntArray, target: Int): Boolean {
//
//        }
//    }

    // LeetCode 153. Find Minimum in Rotated Sorted Array I
    class FindMinWithoutDuplicates : BinarySearchFindMin {

        override fun findMin(nums: IntArray): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            val reference = nums[end]
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > reference -> start = mid + 1
                    else -> end = mid
                }
            }
            return nums[end]
        }
    }

    // LeetCode 154. Find Minimum in Rotated Sorted Array II
    class FindMinWithDuplicates : BinarySearchFindMin {

        override fun findMin(nums: IntArray): Int {
            if (nums.isEmpty()) return -1
            var start = 0
            var end = nums.size - 1
            while (start < end) {
                val mid = start + (end - start) / 2
                when {
                    nums[mid] > nums[end] -> start = mid + 1
                    nums[mid] < nums[end] -> end = mid
                    else -> end--
                }
            }
            return nums[end]
        }
    }

}