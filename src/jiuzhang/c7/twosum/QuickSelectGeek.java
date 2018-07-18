package Jiuzhang.C7.TwoSum;

public class QuickSelectGeek {
	
    // This function returns k'th smallest element in arr[l..r]
    // using QuickSort based method.  ASSUMPTION: ALL ELEMENTS
    // IN ARR[] ARE DISTINCT
    public int kthSmallest(int arr[], int l, int r, int k)
    {
        // If k is smaller than number of elements in array
        if (k > 0 && k <= r + 1)
        {
            // Partition the array around a random element and
            // get position of pivot element in sorted array
//            int pos = randomPartition(arr, l, r);
        	int pos = partition(arr, l, r);
        	
            // If position is same as k
            if (pos == k - 1)
                return arr[pos];
            // If position is more, recur for left subarray
            if (pos > k - 1)
                return kthSmallest(arr, l, pos - 1, k);
            // Else recur for right subarray
            return kthSmallest(arr, pos + 1, r, k);
        }
        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }
 
    // Utility method to swap arr[i] and arr[j]
    void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    // Standard partition process of QuickSort().  It considers
    // the last element as pivot and moves all smaller element 
    // to left of it and greater elements to right. This function
    // is used by randomPartition()
    int partition(int arr[], int l, int r) {
        int pivot = arr[r], i = l;
        for (int j = l; j <= r - 1; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
		for(int k = 0; k < arr.length; k++){
			System.out.print(arr[k] + "  ");
		}
		System.out.print("\t" + "start: " + l + ", end: " + r + ", pivot: " + pivot + ", pointer : " + i);
		System.out.println();
        return i;
    }
 
    // Picks a random pivot element between l and r and 
    // partitions arr[l..r] arount the randomly picked 
    // element using partition()
    int randomPartition(int arr[], int l, int r) {
        int n = r-l+1;
        int pivot = (int)(Math.random()) % n;
        swap(arr, l + pivot, r);
        return partition(arr, l, r);
    }
 
    // Driver method to test above
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSelectGeek one = new QuickSelectGeek();
		int[] arr = {3,4,1,2,5};
		int k = 3;
        for(int i = 0; i < arr.length; i++){
        	System.out.print(arr[i] + "  ");
        }
        System.out.println();
		System.out.println(one.kthSmallest(arr, 0, arr.length - 1, k));
	}

}
