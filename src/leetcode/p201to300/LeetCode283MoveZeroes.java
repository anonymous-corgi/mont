package leetcode.p201to300;

public class LeetCode283MoveZeroes {
  
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    int left = -1;
    int cursor = 0;
    while (cursor < nums.length) {
      if (nums[cursor] != 0) {
        int temp = nums[++left];
        nums[left] = nums[cursor];
        nums[cursor] = temp;
      }
      cursor++;
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
