package algorithm.leetcode.p651to700;

import java.util.HashSet;
import java.util.Set;

public class LeetCode67924Game {

  public boolean judgePoint24(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    @SuppressWarnings("unchecked")
    HashSet<Double>[] set = new HashSet[1 << 4];
    for (int i = 0; i < nums.length; i++) {
      set[1 << i] = new HashSet<Double>();
      set[1 << i].add((double)nums[i]);
    }
    for (int i = 3; i < set.length; i++) {
      if (set[i] == null) {
        set[i] = new HashSet<Double>();
      }
      for (int j = 1; j < i; j++) {
        if ((j & i) == j) {
          helper(set[j], set[i - j], set[i]);
        }
      }
    }
    for (Double d : set[set.length - 1]) {
      if (Math.abs(24 - d) < 0.0001) {
        return true;
      }
    }
    return false;
  }
  
  private void helper(Set<Double> fs, Set<Double> bs, Set<Double> res) {
    for (Double f : fs) {
      for (Double b : bs) {
        res.add(f + b);
        res.add(f - b);
        res.add(f * b);
        res.add(f / b);
      }
    }
  }

  public static void main(String[] args) {
    LeetCode67924Game one = new LeetCode67924Game();
    int[] nums = {3, 3, 8, 8};//True
//    int[] nums = {1, 9, 1, 2};//True
//    int[] nums = {3, 4, 6, 7};//False
    System.out.println(one.judgePoint24(nums));
  }

}
