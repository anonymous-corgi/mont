package leetcode.p251to300;

public class LeetCode275HIndexII {
  
  public int hIndex(int[] nums) {
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == nums.length - mid) {
        return nums[mid];
      } else if (nums[mid] > nums.length - mid) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return nums.length - start;
  }

  public static void main(String[] args) {

  }

}
