package leetcode.p201to250;

public class LeetCode215KthLargestElementInAnArray {
  
  public int findKthLargest(int[] nums, int k) {
    if (nums == null || nums.length < k) {
      return -1;
    }
    return findKth(nums, 0, nums.length - 1, k);
  }
  
  private int findKth(int[] nums, int start, int end, int k) {
    int left = start - 1;
    int cursor = start;
    int pivot = nums[end];
    while (cursor <= end) {
      if (nums[cursor] >= pivot) {
        swap(nums, ++left, cursor);
      }
      cursor++;
    }
    if (k - 1 < left) {
      return findKth(nums, start, left - 1, k);
    } else if (k - 1 > left) {
      return findKth(nums, left + 1, end, k);
    } else {
      return nums[k - 1];
    }
  }
  
  private void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode215KthLargestElementInAnArray one = 
        new LeetCode215KthLargestElementInAnArray();
    int[] nums = {3,2,1,5,6,4};
    int k = 2;
    System.out.println(one.findKthLargest(nums, k));
  }

}
