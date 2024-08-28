package com.anonymouscorgi.karakoram.jiuzhang.c7.twopointers;

public class LintCode101RemoveDuplicatesfromSortedArrayII {
  
  public int removeDuplicates(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int len = 1;
    int repeated = nums[0] - 1;
    for (int i = 1, nLen = nums.length; i < nLen; i++) {
      if (nums[i] == nums[i - 1]) {
        if (nums[i] != repeated) {
          repeated = nums[i];
          nums[len++] = nums[i];
        }
      } else {
        nums[len++] = nums[i];
      }
    }
    return len;
  }
  
//  public int removeDuplicates(int[] nums) {
//    // write your code here
//    if (nums == null || nums.length == 0) {
//      return 0;
//    }
//    int len = 1;
//    int repeated = nums[0] - 1;
//    for (int i = 1, nLen = nums.length; i < nLen; i++) {
//      if (nums[i] == nums[len - 1]) {
//        if (nums[i] != repeated) {
//          repeated = nums[i];
//          move(nums, len++, i);
//        }
//      } else {
//        move(nums, len++, i);
//      }
//    }
//    return len;
//  }
//  
//  private void move(int[] nums, int to, int from) {
//    int temp = nums[to];
//    nums[to] = nums[from];
//    nums[from] = temp;
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
