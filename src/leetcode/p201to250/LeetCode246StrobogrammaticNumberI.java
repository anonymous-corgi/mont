package leetcode.p201to250;

public class LeetCode246StrobogrammaticNumberI {
  
  private final int[] STRO = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};
  
  public boolean isStrobogrammatic(String num) {
    if (num == null || num.length() == 0) {
      return false;
    }
    int left = 0;
    int right = num.length() - 1;
    while (left <= right) {
      int l = num.charAt(left) - '0';
      int r = num.charAt(right) - '0';
      if (STRO[l] != r) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

}
