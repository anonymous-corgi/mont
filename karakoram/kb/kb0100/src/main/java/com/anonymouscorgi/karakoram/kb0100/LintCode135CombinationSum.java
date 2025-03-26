package com.anonymouscorgi.karakoram.kb0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LintCode 135 · Combination Sum
 * <p>
 * Description Given a set of candidate numbers candidates and a target number target. Find all
 * unique combinations in candidates where the numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Example
 * <p>
 * Example 1: Input: candidates = [2, 3, 6, 7], target = 7 Output: [[7], [2, 2, 3]]
 * <p>
 * Example 2: Input: candidates = [1], target = 3 Output: [[1, 1, 1]]
 */
interface LintCode135CombinationSum {

  List<List<Integer>> combinationSum(int[] candidates, int target);

  LintCode135CombinationSum DFS = new LintCode135CombinationSum() {

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (candidates == null || candidates.length == 0) {
        return results;
      }

      Arrays.sort(candidates);
      findCombination(candidates, target, 0, new ArrayList<>(), results);
      return results;
    }

    private void findCombination(
        int[] nums,
        int target,
        int startIndex,
        List<Integer> resultBuilder,
        List<List<Integer>> results) {
      if (target == 0) {
        results.add(new ArrayList<>(resultBuilder));
      }

      for (int i = startIndex; i < nums.length; i++) {
        if (i > startIndex && nums[i] == nums[i - 1]) {
          continue;
        }
        if (nums[i] > target) {
          break;
        }
        resultBuilder.add(nums[i]);
        findCombination(nums, target - nums[i], i, resultBuilder, results);
        resultBuilder.remove(resultBuilder.size() - 1);
      }
    }
  };
}
