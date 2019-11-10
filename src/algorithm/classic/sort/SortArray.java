package algorithm.classic.sort;

@SuppressWarnings("unused")
class SortArray {

    private static abstract class AbstractSortArray implements Sort.Array {

        void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }

    private static final class QuickSort extends AbstractSortArray {

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
    }

    private static final class HeaPSort extends AbstractSortArray {

        @Override
        public void sort(int[] nums) {
            int len = nums.length;

            // Build a MaxHeap
            for (int i = len / 2 - 1; i >= 0; i--) {
                heapify(nums, len, i);
            }

            // One by one extract the max number from heap, and rearrange the array.
            for (int i = len - 1; i >= 0; i--) {
                // Move the max number to end.
                swap(nums, 0, i);

                // Call Heapify function to rearrange the disordered number.
                heapify(nums, i, 0);
            }
        }

        // To heapify a subtree rooted with node i. n is size of heap
        // Heapify function works only when node i is the only disordered node within this subtree.
        private void heapify(int[] nums, int len, int i) {
            int maxIndex = i;  // Initialize largest as root
            int lIndex = 2 * i + 1;  // left = 2 * i + 1
            int rIndex = 2 * i + 2;  // right = 2 * i + 2

            // If left child is larger than root
            if (lIndex < len && nums[lIndex] > nums[maxIndex]) {
                maxIndex = lIndex;
            }

            // If right child is larger than largest so far
            if (rIndex < len && nums[rIndex] > nums[maxIndex]) {
                maxIndex = rIndex;
            }

            // If largest is not root
            if (maxIndex != i) {
                swap(nums, i, maxIndex);

                // Recursively heapify the affected sub-tree
                heapify(nums, len, maxIndex);
            }
        }
    }
}
