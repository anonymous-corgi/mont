package jiuzhang.c2.xxoo;

public class M159FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
        if(nums == null || nums.length ==0){
            return -1;
        }
        
        int head = 0;
        int tail = nums.length - 1;
        while(head + 1 < tail) {
        		int mid = head + (tail - head) / 2;
        		if(nums[mid] > nums[nums.length - 1]) {
        			head = mid;
        		}else {
        			tail =  mid;
        		}
        }
        if(nums[head] <= nums[nums.length - 1]) {
        		return head;
        }else {
        		return tail;
        }
	}
//    public int findMin(int[] nums) {
//        if(nums == null || nums.length ==0){
//            return -1;
//        }
//        
//        return findMin(nums, nums[nums.length - 1], 0, nums.length - 1, -1);
//    }
//    
//    private int findMin(int[] nums, int reference, int head, int end, int lastResult){
//        if(head > end){
//            return lastResult;
//        }
//        
//        int mid = head + (end - head) / 2;
//        if(nums[mid] <= reference){
//            return findMin(nums, reference, head, mid - 1, nums[mid]);
//        }else{
//            return findMin(nums, reference, mid + 1, end, lastResult);
//        }
//    }
    public static void main(String[] args) {
		int[] nums = {1};
		M159FindMinimumInRotatedSortedArray one = new M159FindMinimumInRotatedSortedArray();
		System.out.println(one.findMin(nums));
    }
}
