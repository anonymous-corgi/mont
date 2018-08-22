package leetcode.p251to300;

public class LeetCode274HIndex {
  
  public int hIndex(int[] nums) {
    int sum = 0;
    int[] count = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      int index = nums[i] > nums.length 
          ? nums.length : nums[i];
      count[index]++;
    }
    for (int i = nums.length; i >= 0; i--) {
      sum += count[i];
      if (sum >= i) {
        return i;
      }
    }
    return 0;
  }

}
