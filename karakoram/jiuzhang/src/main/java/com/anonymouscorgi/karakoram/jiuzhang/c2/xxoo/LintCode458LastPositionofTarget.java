package com.anonymouscorgi.karakoram.jiuzhang.c2.xxoo;

public class LintCode458LastPositionofTarget {
  
  public int lastPosition(int[] nums, int target) {
    // write your code here
    // write your code here
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start + 1) / 2;
      if (nums[mid] > target) {
        end = mid - 1;
      } else {
        start = mid;
      }
    }
    return nums[start] == target ? start : -1;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LintCode458LastPositionofTarget one = 
        new LintCode458LastPositionofTarget();
    int[] nums = {1, 2, 3};
    int target = 1;
    System.out.println(one.lastPosition(nums, target));
  }

}
