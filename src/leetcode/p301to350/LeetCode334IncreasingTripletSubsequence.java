package leetcode.p301to350;

public class LeetCode334IncreasingTripletSubsequence {
  
  public boolean increasingTriplet(int[] nums) {
    int min1 = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;
    for (int num : nums) {
      if (num <= min1) {
        min1 = num;
      } else if (num <= min2) {
        min2 = num;
      } else {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {

  }

}
