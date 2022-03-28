package algorithm.leetcode.p151to200;

import java.util.ArrayList;
import java.util.List;

public class LeetCode163MissingRanges {
  
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> res = new ArrayList<>();
    if (nums == null) {
      return res;
    }
    long last = (long) lower - 1;
    for (int i = 0; i < nums.length; i++) {
      addRange((long) nums[i], last, res);
      last = (long) nums[i];
    }
    addRange((long) upper + 1, last, res);
    return res;
  }
  
  private void addRange(long current, long last, List<String> res) {
    long diff = current - last;
    if (diff == 2)  {
      res.add("" + (current - 1));
    } else if (diff > 2) {
      res.add("" + (last + 1) + "->" + (current - 1));
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
