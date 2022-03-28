package algorithm.interview.linkedin;

import static org.junit.Assert.*;
import org.junit.Test;

public class MaximunDifferenceInAnArray {
  
  public int findMaxDiff(int[] nums) {
    if (nums.length < 2) {
      return -1;
    }
    int min = nums[0];
    int diff = 0;
    for (int i = 1; i < nums.length; i++) {
      diff = Math.max(diff, nums[i] - min);
      min = Math.min(min, nums[i]);
    }
    return diff == 0 ? -1 : diff; 
  }
  
  @Test
  public void testFindMaxDiff1() {
    int[] nums = {2,3,10,2,4,8,1};
    int res = 8;
    assertEquals(res, findMaxDiff(nums));
  }
  
  @Test
  public void testFindMaxDiff2() {
    int[] nums = {1,2,6,4};
    int res = 5;
    assertEquals(res, findMaxDiff(nums));
  }
  
  @Test
  public void testFindMaxDiff3() {
    int[] nums = {6,6,6,6};
    int res = -1;
    assertEquals(res, findMaxDiff(nums));
  }
  
  @Test
  public void testFindMaxDiff4() {
    int[] nums = {6,5,3,2};
    int res = -1;
    assertEquals(res, findMaxDiff(nums));
  }

  public static void main(String[] args) {
    int[] nums = {6,2,1,4};
    MaximunDifferenceInAnArray one =
        new MaximunDifferenceInAnArray();
    System.out.println(one.findMaxDiff(nums));
  }

}
