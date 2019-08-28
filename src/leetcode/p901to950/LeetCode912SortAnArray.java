package leetcode.p901to950;

@SuppressWarnings("unused")
public class LeetCode912SortAnArray {

    private interface Method {
        int[] sortArray(int[] nums);
    }

    private static class QuickSort implements Method {

        @Override
        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }
            int left = start - 1;
            int right = end + 1;
            int pivot = nums[end];
            for (int cursor = start; cursor < right; ) {
                if (nums[cursor] < pivot) {
                    swap(nums, ++left, cursor++);
                } else if (nums[cursor] > pivot) {
                    swap(nums, cursor, --right);
                } else {
                    cursor++;
                }
            }

            quickSort(nums, start, left);
            quickSort(nums, right, end);
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
