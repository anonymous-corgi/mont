package leetcode.dfs;

import utils.Print;

public class LastPermutation {
  
  private int[] lastPermutation(int[] nums){
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int smallestHead = nums.length - 1;
    //Find the toBeChanged that make subArray[smallestHead, nums.length - 1] 
    //is the smallest permutation of the subArray.
    while (smallestHead > 0 && nums[smallestHead - 1] <= nums[smallestHead]) {
      smallestHead--;
    }
    if (smallestHead == 0) {
      swapRange(nums, 0, nums.length);
    } else {
      swapRange(nums, smallestHead, nums.length - 1);
      int pivot = nums[smallestHead - 1];
      //Find the largest number that is smaller than nums[smallestHead - 1]
      //and swap their positions.
      int firstSmaller = nums.length - 1;
      while (pivot <= nums[firstSmaller]) {
        firstSmaller--;
      }
      swap(nums, smallestHead - 1, firstSmaller);
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
    LastPermutation one = new LastPermutation();
    int[] nums = {1, 3, 4, 2};
    Print.printArray(one.lastPermutation(nums));
  }

}
