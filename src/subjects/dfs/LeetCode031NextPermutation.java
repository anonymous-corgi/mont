package subjects.dfs;

import utils.Print;

public class LeetCode031NextPermutation {
	
  private int[] nextPermutation(int[] nums){
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int largestHead = nums.length - 1;
    //Find the toBeChanged that make subArray[largestHead, nums.length - 1] 
    //is the largest permutation of the subArray.
    while (largestHead > 0 && nums[largestHead - 1] >= nums[largestHead]) {
      largestHead--;
    }
    if (largestHead == 0) {
      swapRange(nums, 0, nums.length - 1);
    } else {
      swapRange(nums, largestHead, nums.length - 1);
      int pivot = nums[largestHead - 1];
      //Find the smallest number that is larger than nums[toBeChanged - 1]
      //and swap their positions.
      int firstLarger = largestHead;
      while (nums[firstLarger] <= pivot) {
        firstLarger++;
      }
      swap(nums, largestHead - 1, firstLarger);
    }
    return nums;
  }
  
  private void swapRange(int[] nums, int start, int end) {
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
    LeetCode031NextPermutation one = new LeetCode031NextPermutation();
    int[] nums = {3, 2, 4, 1};
    Print.printArray(one.nextPermutation(nums));
  }

}
