package jiuzhang.sort;

public class HeapSort {
	
	public void heapsort(int nums[])
    {
        int len = nums.length;
 
        // Build heap (rearrange array)
        for (int i = len / 2 - 1; i >= 0; i--)
            heapify(nums, len, i);
 
        // One by one extract an element from heap
        for (int i = len - 1; i >= 0; i--)
        {
            // Move current root to end
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(nums, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int nums[], int len, int index)
    {
        int maxIndex = index;  // Initialize largest as root
        int left = 2 * index + 1;  // left = 2*i + 1
        int right = 2 * index + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (left < len && nums[left] > nums[maxIndex])
            maxIndex = left;
 
        // If right child is larger than largest so far
        if (right < len && nums[right] > nums[maxIndex])
            maxIndex = right;
 
        // If largest is not root
        if (maxIndex != index) {
            int swap = nums[index];
            nums[index] = nums[maxIndex];
            nums[maxIndex] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(nums, len, maxIndex);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
