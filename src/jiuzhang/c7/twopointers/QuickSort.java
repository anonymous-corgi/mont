package jiuzhang.c7.twopointers;

public class QuickSort {

	/* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
	public void sort(int arr[], int low, int high) {
		if (low < high)	{
			/* phoneinterview is partitioning index, arr[phoneinterview] is
              now at right place */
			int pi = partition(arr, low, high);
			printArray(arr);
			// Recursively sort elements before
			// partition and after partition
			sort(arr, low, pi-1);
			sort(arr, pi+1, high);
		}
	}
	
	private int partition(int arr[], int low, int high)	{
		int pivot = arr[high]; 
		int i = (low-1); // index of smaller element
		for (int j=low; j<high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot) {
				i++;
				
				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		return i+1;
	}
 
 
 
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSort one = new QuickSort();
		int[] arr = {2,1,2,3};
		one.sort(arr, 0, arr.length - 1);

	}

}
