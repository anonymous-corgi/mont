package jiuzhang.sort;

import utils.Print;

public class HeapSort {

    public void heapsort(int[] nums) {
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
    void heapify(int[] nums, int len, int i) {
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

    private void swap(int[] nums, int x, int y) {
        int swap = nums[x];
        nums[x] = nums[y];
        nums[y] = swap;
    }

    public static void main(String[] args) {
        HeapSort one = new HeapSort();
        int[] array = new int[]{8, 6, 7, 5, 3, 1, 4, 2, 9};
        one.heapsort(array);
        Print.printArray(array);
    }
}
