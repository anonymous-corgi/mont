package algorithm.leetcode.p451to500;

import java.util.HashMap;
import java.util.Map;

public class LeetCode494TargetSum {
  
  public int findTargetSumWays(int[] nums, int S) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      map = helper(map, nums[i]);
    }
    return map.get(S) != null ? map.get(S) : 0;
  }
  
  private Map<Integer, Integer> helper(Map<Integer, Integer> set, int num) {
    Map<Integer, Integer> neoSet = new HashMap<>();
    for (Integer prev : set.keySet()) {
      int sum = prev + num;
      neoSet.put(sum, neoSet.getOrDefault(sum, 0) + set.get(prev));      
      sum = prev - num;
      neoSet.put(sum, neoSet.getOrDefault(sum, 0) + set.get(prev));
    }
    return neoSet;
  }

  public static void main(String[] args) {
    LeetCode494TargetSum one = 
        new LeetCode494TargetSum();
    int[] nums = {1, 1, 1, 1, 1};
    int S = 3;
    System.out.println(one.findTargetSumWays(nums, S));
  }

}
