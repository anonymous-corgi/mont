package algorithm.leetcode.p251to300;

import java.util.Arrays;

public class LeetCode2593SumSmaller {
  
  public int threeSumSmaller(int[] nums, int target) {
    // Write your code here
    if (nums == null || nums.length < 3) {
      return 0;
    }
    int count = 0;
    Arrays.sort(nums);
    for (int i = nums.length - 1; i >= 2; i--) {
      int left = 0; 
      int right = i - 1;
      int st = target - nums[i];
      while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum < st) {
          count += (right - left);
          left++;
        } else {
          right--;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
