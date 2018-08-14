package leetcode.p001to050;

public class LeetCode045JumpGameII {
  
  public int jump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = 0;
    int jump = 0;
    while (end < nums.length - 1) {
      jump++;
      int farthest = end;
      for (int i = start; i <= end; i++) {
        farthest = Math.max(farthest, nums[i] + i);
      }
      if (farthest == end) {
        return -1;
      }
      start = end + 1; 
      end = farthest;
    }
    return jump;
  }

  public static void main(String[] args) {
    int[] nums = {2,0,0,1};
    LeetCode045JumpGameII one =
        new LeetCode045JumpGameII();
    System.out.println(one.jump(nums));
  }

}
