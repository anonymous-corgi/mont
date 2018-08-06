package lintcodespecials;

import java.util.HashSet;
import java.util.Set;

public class LintCode1368SameNumber {
  
  public String sameNumber(int[] nums, int k) {
    // Write your code here
    if (nums == null || nums.length == 0) {
      return "NO";
    }
    k--;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < k; i++) {
      if (!set.add(nums[i])) {
        return "YES";
      }
    }
    for (int i = k; i < nums.length; i++) {
      if (!set.add(nums[i])) {
        return "YES";
      }
      set.remove(nums[i - k]);
    }
    return "NO";
  }

  public static void main(String[] args) {
    LintCode1368SameNumber one = new LintCode1368SameNumber();
    int[] nums = {16,38,43,23,29,6,49,9,41,27,6,19,30,23,44,25,0,14,27,44,32,16,39,11,6,41,34,43,35,22};
    int k = 5;
    one.sameNumber(nums, k);
  }

}
