package algorithm.sort.arraylist;

import algorithm.sort.Sort;

public class QuickSort implements Sort.Array {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start - 1;
        int right = end + 1;
        int pivot = nums[end];
        int cursor = start;
        while (cursor < right) {
            if (nums[cursor] < pivot) {
                swap(nums, ++left, cursor++);
            } else if (nums[cursor] > pivot) {
                swap(nums, --right, cursor);
            } else {
                cursor++;
            }
        }

        quickSort(nums, start, left);
        quickSort(nums, right, end);
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
