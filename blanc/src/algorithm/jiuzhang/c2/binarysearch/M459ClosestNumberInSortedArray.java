package algorithm.jiuzhang.c2.binarysearch;

public class M459ClosestNumberInSortedArray {
    public int closestNumber(int[] nums, int target) {
    		if(nums == null || nums.length == 0) {
    			return -1;
    		}
    		
    		int start = 0;
    		int end = nums.length - 1;
    		while(start + 1 < end) {
    			int mid = start + (end - start) / 2;
    			if(nums[mid] < target) {
    				start = mid;
    			}else if(nums[mid] > target) {
    				end = mid;
    			}else{
    				return mid;
    			}
    		}
    		if(Math.abs(nums[start] - target) < Math.abs(nums[end] - target)) {
    			return start;
    		}else {
    			return end;
    		}
    }
    
//    public int closestNumber(int[] nums, int target) {
//        if(nums == null || nums.length == 0) {
//            return -1;
//        }
//        
//        return binarySearch(nums, target, 0, nums.length - 1, 0);
//    }
//    
//    private int binarySearch(int[] nums, int target, int start, int end, int closest) {
//        if(start > end){
//            return closest;
//        }
//        int mid = start + (end - start) / 2;
//        int neoClosest = closest;
//        if(Math.abs(nums[mid] - target) < Math.abs(nums[closest] - target)) {
//            neoClosest = mid;
//        }
//        
//        if(nums[mid] < target){
//            return binarySearch(nums, target, mid + 1, end, neoClosest);
//        }else if(nums[mid] > target) {
//            return binarySearch(nums, target, start, mid - 1, neoClosest);
//        }else {
//            return mid;
//        }
//    }
    
    
    
    public static void main(String[] args) {
		int[] nums = {1, 2, 3,  6, 7, 8, 9};
		M459ClosestNumberInSortedArray one = new M459ClosestNumberInSortedArray();
		System.out.println(one.closestNumber(nums, 4));
    }
}
