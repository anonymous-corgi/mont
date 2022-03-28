package algorithm.interview.linkedin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SubarraySum {
  
  public int getSubarraySum(int[] nums) {
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res += (i + 1) * (nums.length - i) * nums[i];
    }
    return res;
  }
  
  @Test
  public void testGetSubarraySum1() {
    int[] nums = {1,2,3};
    int res = 20;
    assertEquals(res, getSubarraySum(nums));
  }

  public static void main(String[] args) {

  }

}
