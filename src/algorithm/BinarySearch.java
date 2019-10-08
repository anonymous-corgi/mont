package algorithm;

public class BinarySearch {

    private interface Search {
        int search(int[] nums);
    }

    private interface SearchTarget {
        int search(int[] nums, int target);
    }


    /**
     * ///////////////////////////////////////// CLASSIC BINARY SEARCH /////////////////////////////////////////
     */

    // LeetCode 704. Binary Search /////////////////////////////////////////////////////////////////////////////////////

    /**
     * while (start <= end)
     */
    private static class Classic_Equal implements SearchTarget {

        @Override
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < target) {
                    start = mid + 1;
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }

    /**
     * while (start < end)
     * <p>
     * 1. STOP STATE:
     * For start < end, start = mid + 1, end = mid - 1,
     * the stop state not only happens at (start == end), but also (start == end + 1).
     * But for start < end, one side is start = mid or end = mid,
     * the stop state is only start == end.
     * 2. INDEX OUT OF BOUNDARY:
     * At stop state, (mid = start + 1) or (mid = end - 1) may have INDEX OUT OF BOUNDARY Exception.
     * It happen when 1. mid = start + (end - start) / 2, then (mid - 1) might be -1.
     * 2. mid = start + (end - start + 1) / 2, then (mid - 1) might be nums.length.
     * 3. INFINITE LOOP problem
     * It happen when 1. mid = start + (end - start) / 2,  mid is EQUAL TO start. start = mid will cause INFINITE LOOP.
     * 2. mid = start + (end - start + 1) / 2,  mid is EQUAL TO end, end = mid will cause INFINITE LOOP.
     * So for start < end, if mid = start + (end - start) / 2, must have start = mid + 1.
     * if mid = start + (end - start + 1) / 2, must have end = mid - 1;
     */
    private static class Classic implements SearchTarget {

        @Override
        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
             // int mid = start + (end - start + 1) / 2;
                if (nums[mid] < target) {
                    start = mid + 1;
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    return mid;
                }
            }
            return nums[start] == target ? start : -1;
         // return nums[end] == target ? end : -1;
        }
    }


    /**
     * /////////////////////////////////////////// SEARCH A 2D MATRIX ///////////////////////////////////////////
     * {@link leetcode.p051to100.LeetCode074SearchA2DMatrix}
     * {@link leetcode.p201to250.LeetCode240SearchA2DMatrixII}
     */


    /**
     * /////////////////////////////////////////  SEARCH FIRST or LAST  /////////////////////////////////////////
     */

    private static class SearchFirstTarget implements Search {

        @Override
        public int search(int[] nums) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (isQualified(nums, mid)) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return isQualified(nums, end) ? end : -1;
        }

        private boolean isQualified(int[] nums, int mid) {
            return true;
        }
    }

    private static class SearchLastTarget implements Search {

        @Override
        public int search(int[] nums) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start + 1) / 2;
                if (isQualified(nums, mid)) {
                    start = mid;
                } else {
                    end = mid - 1;
                }
            }
            return isQualified(nums, start) ? start : -1;
        }

        private boolean isQualified(int[] nums, int mid) {
            return true;
        }
    }

    /**
     * ///////////////////////////////////// SEARCH IN ROTATED SORTED ARRAY /////////////////////////////////////
     */

    // LeetCode 033. Search in Rotated Sorted Array
    private static class SearchInRotatedArray implements SearchTarget {

        @Override
        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            int pivot = nums[end];
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < target) {
                    if (nums[mid] <= pivot && pivot < target) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    if (target <= pivot && pivot < nums[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    return mid;
                }
            }
            return nums[start] == target ? start : -1;
        }
    }

    /**
     * Duplicate makes it hard to tell whether nums[mid] is in front part or back part when nums[mid] == pivot.
     * For example: [2, 2, 3, 4, 1, 2, 2] if we find nums[mid] == 2, we can't tell if mid is in the front or back.
     */

    // LeetCode 081. Search in Rotated Sorted Array II - Solution1: Trim head at first.
    private static class SearchInRotatedArrayWithDuplicates implements SearchTarget {

        @Override
        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            while (end > 0 && nums[start] == nums[end]) {
                end--;
            }
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < target) {
                    if (nums[mid] <= nums[end] && nums[end] < target) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    if (target <= nums[end] && nums[end] < nums[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    return mid;
                }
            }
            return nums[start] == target ? start : -1;
        }
    }

    // LeetCode 081. Search in Rotated Sorted Array II - Solution2: Dynamically Moving Reference.
    private static class SearchInRotatedArrayWithDuplicates2 implements SearchTarget {

        @Override
        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < target) {
                    if (nums[mid] == nums[end]) {
                        end--;
                    } else if (nums[mid] <= nums[end] && nums[end] < target) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    if (nums[mid] == nums[end]) {
                        end--;
                    } else if (target <= nums[end] && nums[end] < nums[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    return mid;
                }
            }
            return nums[start] == target ? start : -1;
        }
    }


    /**
     * ////////////////////////////////// FIND MINIMUM IN ROTATED SORTED ARRAY //////////////////////////////////
     */

    // LeetCode 153. Find Minimum in Rotated Sorted Array I
    private static class FindMinWithoutDuplicates implements Search {

        @Override
        public int search(int[] nums) {
            int start = 0;
            int end = nums.length - 1;
            int pivot = nums[end];
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] <= pivot) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return nums[end];
        }
    }

    // LeetCode 154. Find Minimum in Rotated Sorted Array II
    private static class FindMinWithDuplicates implements Search {

        @Override
        public int search(int[] nums) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < nums[end]) {
                    end = mid;
                } else if (nums[mid] > nums[end]) {
                    start = mid + 1;
                } else {
                    end--;
                }
            }
            return nums[end];
        }
    }
}
