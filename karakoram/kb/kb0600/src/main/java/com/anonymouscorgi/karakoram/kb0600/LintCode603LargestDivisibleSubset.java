package com.anonymouscorgi.karakoram.kb0600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LintCode 603 · Largest Divisible Subset
 * <p>
 * Description Given a set of distinct positive integers, find the largest subset which has the most
 * elements, and every pair of two elements (Si, Sj) in this subset satisfies: Si % Sj = 0 or Sj %
 * Si = 0.
 * <p>
 * Example 1: Input: nums =  [1,2,3], Output: [1,2] or [1,3]
 * <p>
 * Example 2: Input: nums = [1,2,4,8], Output: [1,2,4,8]
 */
interface LintCode603LargestDivisibleSubset {

  List<Integer> largestDivisibleSubset(int[] nums);

  LintCode603LargestDivisibleSubset Method = new LintCode603LargestDivisibleSubset() {

    @Override
    public List<Integer> largestDivisibleSubset(int[] nums) {
      if (nums == null || nums.length == 0) {
        return new ArrayList<>();
      }
      Arrays.sort(nums);
      Map<Integer, List<Integer>> map = new HashMap<>();
      for (Integer num : nums) {
        if (!map.containsKey(num)) {
          List<Integer> list = new ArrayList<>();
          list.add(num);
          map.put(num, list);
        }
      }
      int[] maxSize = new int[nums.length];
      for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
          if (nums[i] % nums[j] == 0) {
            if (maxSize[j] + 1 > maxSize[i]) {
              map.get(nums[i]).clear();
              map.get(nums[i]).addAll(map.get(nums[j]));
              map.get(nums[i]).add(nums[i]);
              maxSize[i] = maxSize[j] + 1;
            }
          }
        }
      }
      int max = Integer.MIN_VALUE;
      int maxIndex = 0;
      for (int t = 0; t < maxSize.length; t++) {
        if (maxSize[t] > max) {
          max = maxSize[t];
          maxIndex = t;
        }
      }
      return map.get(nums[maxIndex]);
    }
  };
}
