package jiuzhang.c2.binarysearch;

public class E457ClassicalBinarySearch {
	public int findPosition(int[] nums, int target){
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int head = 0;
		int tail = nums.length - 1;
		while(head + 1 < tail) {
			int mid = (head + tail) / 2;
			if(nums[mid] > target) {
				tail = mid;
			}else if(nums[mid] < target) {
				head = mid;
			}else {
				head = mid;
				tail = mid;
			}
		}
		if(nums[head] == target){
			return head;
		}
		if(nums[tail] == target){
			return tail;
		}
		return -1;
		
	}

	
//	public int findPosition(int[] nums, int target) {
//		if(nums == null || nums.length == 0) {
//			return -1;
//		}
//		return findPosition(nums, target, 0, nums.length - 1);
//	}
//	
//	private int findPosition(int[] nums, int target, int head, int tail) {
//		if(head > tail) {
//			return -1;
//		}
//		
//		int mid = (head + tail) / 2;
//		if(nums[mid] > target) {
//			return findPosition(nums, target, head, mid - 1);
//		}else if (nums[mid] < target) {
//			return findPosition(nums, target, mid + 1, tail);
//		}else {
//			return mid;
//		}
//	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		E457ClassicalBinarySearch one = new E457ClassicalBinarySearch();
		System.out.println(one.findPosition(nums, 4));
	}
}
