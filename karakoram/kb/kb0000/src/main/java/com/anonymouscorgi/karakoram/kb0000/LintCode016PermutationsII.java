package com.anonymouscorgi.karakoram.kb0000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LintCode 16 · Permutations II
 * <p>
 * Description Given a list of numbers with duplicate numbers in it. Find all unique permutations of
 * it.
 */
interface LintCode016PermutationsII {

  List<List<Integer>> permuteUnique(int[] nums);

  LintCode016PermutationsII DFS = new LintCode016PermutationsII() {

    @Override
    public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null || nums.length == 0) {
        results.add(new ArrayList<>());
        return results;
      }

      Arrays.sort(nums);
      searcher(nums, new boolean[nums.length], new ArrayList<>(), results);
      return results;
    }

    private static void searcher(
        int[] nums,
        boolean[] isUsed,
        List<Integer> resultBuilder,
        List<List<Integer>> results) {
      if (resultBuilder.size() == nums.length) {
        results.add(new ArrayList<>(resultBuilder));
        return;
      }

      for (int i = 0; i < nums.length; i++) {
        if (isUsed[i]) {
          continue;
        }
        if (i != 0 && nums[i] == nums[i - 1] && !isUsed[i - 1]) {
          continue;
        }
        resultBuilder.add(nums[i]);
        isUsed[i] = true;
        searcher(nums, isUsed, resultBuilder, results);
        resultBuilder.remove(resultBuilder.size() - 1);
        isUsed[i] = false;
      }
    }
  };
}
