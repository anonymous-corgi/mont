package leetcode.dfs;

import utils.test.Print;

public class NextPermutation {
	
	private int[] nextPermutation(int[] nums){
		if (nums == null) {
			return new int[0];
		}
		int len = nums.length;
		if (len == 0) {
			return nums;
		}
		
		int indexS = len - 1;
		for (; indexS > 0; indexS--) {
			if (nums[indexS - 1] < nums[indexS]) {
				break;
			}
		}
		if (indexS == 0) {
			swapRange(nums, 0, len - 1);
		} else {
			swapRange(nums, indexS, len - 1);
			int pivot = nums[indexS - 1];
			int index = indexS;
			while (nums[index] <= pivot) {
				index++;
			}
			swap(nums, index, indexS - 1);
		}
		return nums;
	}
	
	private void swapRange(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		while (start < end) {
			swap(nums, start++, end--);
		}
	}
	
	private void swap(int[] nums, int start, int end) {
		int temp = nums[start];
		nums[start] = nums[end];
		nums[end] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NextPermutation one = new NextPermutation();
		int[] nums = {4, 3, 2, 1};
		Print.printArray(one.nextPermutation(nums));
	}

}
