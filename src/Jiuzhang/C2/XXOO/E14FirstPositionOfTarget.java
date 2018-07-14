package Jiuzhang.C2.XXOO;

public class E14FirstPositionOfTarget {
//    public int binarySearch(int[] nums, int target) {
//        if(nums == null || nums.length == 0){
//            return -1;
//        }
//        
//        int start = 0;
//        int end = nums.length - 1;
//        while(start + 1 < end) {
//        		int mid = start + (end - start) / 2;
//        		if(nums[mid] < target) {
//        			start = mid;
//        		}else if(nums[mid] > target) {
//        			end = mid;
//        		}else {
//        			end = mid;
//        		}
//        }
//        if(nums[start] == target) {
//        		return start;
//        }
//        if(nums[end] == target) {
//        		return end;
//        }
//        return -1;
//    }
	public int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
		
        return binarySearch(nums, target, 0, nums.length - 1, -1);
	}
	
	private int binarySearch(int[] nums, int target, int head, int tail, int lastResult) {
		if(head > tail) {
			return lastResult;
		}
		
		int mid = head + (tail - head) / 2;
		if(nums[mid] < target) {
			return binarySearch(nums, target, mid + 1, tail, lastResult);
		}else if(nums[mid] > target) {
			return binarySearch(nums, target, head, mid - 1, lastResult);
		}else{
			return binarySearch(nums, target, head, mid - 1, mid);
		}
	}
//    public int binarySearch(int[] nums, int target) {
//        if(nums == null || nums.length == 0){
//            return -1;
//        }
//        return binarySearch(nums, target, 0, nums.length - 1, -1);
//    }
//    
//    private int binarySearch(int[] nums, int target, int head, int end, int LastResult){
//        if(head > end){
//            return lastResult;
//        }
//        
//        int mid = head + (end - head) / 2;
//        if(nums[mid] == target){
//            return binarySearch(nums, target, head, mid - 1, mid);
////          return lastPosition(nums, target, mid + 1, end, mid); For LastPosition of Target 
//        }else if(nums[mid] < target){
//            return binarySearch(nums, target, mid + 1, end, lastResult);
//        }else{
//            return binarySearch(nums, target, head, mid - 1, lastResult);
//        }
//    }
    

	
	
	
	
    public static void main(String[] args) {
		int[] nums = {1};
//		int[] nums = {1, 2, 3, 3, 4, 4, 4, 5, 6, 7, 8, 9};
		E14FirstPositionOfTarget one = new E14FirstPositionOfTarget();
		System.out.println(one.binarySearch(nums, 1));
    }
    
}
