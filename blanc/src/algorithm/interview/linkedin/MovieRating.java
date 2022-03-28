package algorithm.interview.linkedin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MovieRating {
  
  public int getMovingRating(int[] nums) {
    int pYes = 0;
    int pNo = 0;
    for (int i = 0; i < nums.length; i++) {
      int cYes = Math.max(pNo, pYes) + nums[i];
      int cNo = pYes;
      pNo = cNo;
      pYes = cYes;
    }
    return Math.max(pYes, pNo);
  }
  
  
  @Test
  public void testGetMovingRating1() {
    int[] nums = {9,-1,-3,5,6};
    int res = 19;
    assertEquals(res, getMovingRating(nums));
  }
  
  @Test
  public void testGetMovingRating2() {
    int[] nums = {9,-1,-3,-10,6};
    int res = 12;
    assertEquals(res, getMovingRating(nums));
  }

  public static void main(String[] args) {
    int[] nums = {9,-1,-3,5,6};
    MovieRating one = new MovieRating();
    System.out.println(one.getMovingRating(nums));
  }

}
