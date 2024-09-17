package com.anonymouscorgi.karakoram.kb0000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LintCode 15 · Permutations
 * <p>
 * Description Given a list of unique numbers, return all possible permutations of it.
 */
interface LintCode015Permutations {

  List<List<Integer>> permute(int[] nums);

  LintCode015Permutations DFS = new LintCode015Permutations() {

    @Override
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null && nums.length == 0) {
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
        resultBuilder.add(nums[i]);
        isUsed[i] = true;
        searcher(nums, isUsed, resultBuilder, results);
        resultBuilder.remove(resultBuilder.size() - 1);
        isUsed[i] = false;
      }
    }
  };
}
