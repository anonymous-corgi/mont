package jiuzhang.c7.twosum;

public class M461KthSmallestNumberInUnsortedArray {
	
    public int kthSmallest(int k, int[] nums) {
		if(nums == null || nums.length < k){
			return -1;
		}
		return sort(k, nums, 0, nums.length - 1);
	}
	
	private int sort(int k, int[] nums, int start, int end){
		int pivot = nums[end];
		int pointer = start - 1;
		for(int i = start; i <= end; i++){
			if(nums[i] <= pivot){
				pointer++;
				int temp = nums[pointer];
				nums[pointer] = nums[i];
				nums[i] = temp;
			}
		}
		
		if (pointer < k - 1) {
			return sort(k, nums, pointer + 1, end);
		} else if (pointer >  k - 1) {
			return sort(k, nums, start, pointer - 1); 
		} else {
			return nums[k - 1];
		}
    }
	
	
//    public int kthSmallest(int k, int[] nums) {
//        // write your code here
//        return quickSelect(nums, 0, nums.length - 1, k);
//    }
//
//    public int quickSelect(int[] nums, int start, int end , int k) {
//        
//        if (start == end) {
//            return nums[start];
//        }
//        
//        int left = start;
//        int right = end;
//        int pivot = nums[(start + end) / 2];
//        
//        while (left <= right) {
//            while (left <= right && nums[left] < pivot) {
//                left++;
//            }
//            while (left <= right && nums[right] > pivot) {
//                right--;
//            }
//            
//            if (left <= right) {
//                int temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//                
//                left++;
//                right--;
//            }
//        }
//        
//        /* */
//        for(int i = 0; i < nums.length; i++){
//        	System.out.print(nums[i] + "  ");
//        }
//        System.out.print("\t" + "start: " + start + ", end: " + end + ", pivot: " + pivot + ", left : " + left + ", right : " + right);
//        System.out.println();
//        /* */
//        if (right >= k - 1 && start <= right) {
//            return quickSelect(nums, start, right, k);
//        } else if (left <= k - 1) {
//            return quickSelect(nums, left, end, k);
//        } else {
//            return nums[k - 1];
//        }
//    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M461KthSmallestNumberInUnsortedArray one = new M461KthSmallestNumberInUnsortedArray();
		int[] arr = {3,4,1,2,5};
		
        for(int i = 0; i < arr.length; i++){
        	System.out.print(arr[i] + "  ");
        }
        System.out.println();
		System.out.println(one.kthSmallest(3, arr));

	}
	

}
