package algorithm.leetcode.p501to550;

public class LeetCode540SingleElementInASortedArray {
  
  public int singleNonDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    } else if (nums.length == 1) {
      return nums[0];
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (mid % 2 == 1) {
        mid--;
      }
      if (nums[mid] != nums[mid + 1]) {
        end = mid;
      } else {
        start = mid + 2;
      }
    }
    return nums[start];
  }

  public static void main(String[] args) {
    LeetCode540SingleElementInASortedArray one =
        new LeetCode540SingleElementInASortedArray();
    int[] nums = {1,1,2};
    System.out.println(one.singleNonDuplicate(nums));
  }

}
