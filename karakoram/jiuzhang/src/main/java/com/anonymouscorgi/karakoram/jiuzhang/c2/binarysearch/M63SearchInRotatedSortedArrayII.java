package com.anonymouscorgi.karakoram.jiuzhang.c2.binarysearch;

public class M63SearchInRotatedSortedArrayII {
	
    public boolean search(int[] nums, int target) {
        // write your code here
        if(nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int reference = nums[end];
        while (start < end && nums[start] == reference) {
            start++;
        }
        return (binarySearch(nums, target, reference, start , end) != -1);
    }
    
    private int binarySearch(int[] nums, int target, int reference, int start, int end) {
        if(start > end) {
            return -1;
        }
        
        int mid = start + (end - start) / 2;
        int current = nums[mid];
        if (current == target) {
            return mid;
        }
        if (target > reference) {
            if (nums[mid] > reference && nums[mid] < target) {
                return binarySearch(nums, target, reference, mid + 1, end);
            } else {
                return binarySearch(nums, target, reference, start, mid - 1);
            }
        } else {
            if (nums[mid] > target && nums[mid] <= reference) {
                return binarySearch(nums, target, reference, start, mid - 1);
            } else {
                return binarySearch(nums, target, reference, mid + 1, end);
            }
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M63SearchInRotatedSortedArrayII one = new M63SearchInRotatedSortedArrayII();
		int[] nums = {9,5,6,7,8,9,9,9,9,9,9};
		int target = 8;
		one.search(nums, target);

	}

}
