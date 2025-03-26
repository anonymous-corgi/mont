package com.anonymouscorgi.karakoram.kb0150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LintCode 153 · Combination Sum II
 * <p>
 * Description Given an array num and a number target. Find all unique combinations in num where the
 * numbers sum to target.
 * <p>
 * Example 1:
 *
 * Input: num = [7,1,2,5,1,6,10], target = 8 Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 * <p>
 * Example 2:
 *
 * Input: num = [1,1,1], target = 2 Output: [[1,1]] Explanation: The solution set must not contain
 * duplicate combinations.
 */
interface LintCode153CombinationSumII {

  List<List<Integer>> combinationSum2(int[] num, int target);

  LintCode153CombinationSumII DFS = new LintCode153CombinationSumII() {

    @Override
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null || nums.length == 0) {
        return results;
      }

      Arrays.sort(nums);
      findCombination(nums, target, 0, new ArrayList<>(), results);
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
        return;
      }

      for (int i = startIndex; i < nums.length; i++) {
        if (i > startIndex && nums[i] == nums[i - 1]) {
          continue;
        }
        if (nums[i] > target) {
          break;
        }
        resultBuilder.add(nums[i]);
        findCombination(nums, target - nums[i], i + 1, resultBuilder, results);
        resultBuilder.remove(resultBuilder.size() - 1);
      }
    }
  };
}
