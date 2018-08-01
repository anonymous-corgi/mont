package leetcode.p201to250;

import utils.Print;
//Google
public class LeetCode238ProductOfArrayExceptSelf {
  
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int[] res = new int[nums.length];
    res[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      right *= nums[i + 1];
      res[i] *= right;
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode238ProductOfArrayExceptSelf one = 
        new LeetCode238ProductOfArrayExceptSelf();
    int[] nums = {1, 2, 3, 4};
    Print.printArray(one.productExceptSelf(nums));
  }

}
