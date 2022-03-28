package algorithm.leetcode.p651to700;

public class LeetCode674LongestContinuousIncreasingSubsequence {
  
  public int longestIncreasingContinuousSubsequence(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int max = 1;
    int count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) {
        count++;
        max = Math.max(max, count);
      } else {
        count = 1;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    LeetCode674LongestContinuousIncreasingSubsequence one = 
        new LeetCode674LongestContinuousIncreasingSubsequence();
    int[] A = {5,2,3,4,6,8,8,8,4,5,6,4,3,2,1};
    System.out.println(one.longestIncreasingContinuousSubsequence(A));
  }

}
