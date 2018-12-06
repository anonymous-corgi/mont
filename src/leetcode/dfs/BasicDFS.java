package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.dfs.BasicDFS.CombinationSum.CombinationSumWithDuplicates;
import leetcode.dfs.BasicDFS.Permutation.PermutationWithDuplicates;
import leetcode.dfs.BasicDFS.Subset.SubsetsWithDuplicates;

public class BasicDFS {

  interface Permutation {

    List<List<Integer>> permute(int[] nums);

    class PermutaionWithoutDuplicates implements Permutation {

      @Override
      public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
          return results;
        }
        Arrays.sort(nums);
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int num : nums) {
          queue.offer(num);
        }
        dfs(queue, new ArrayList<>(), results);

        return results;
      }

      private void dfs(Queue<Integer> res, List<Integer> subResult, List<List<Integer>> results) {
        if (res.isEmpty()) {
          results.add(new ArrayList<>(subResult));
          return;
        }
        for (int i = 0, len = res.size(); i < len; i++) {
          Integer cursor = res.poll();
          subResult.add(cursor);
          dfs(res, subResult, results);
          res.offer(cursor);
          subResult.remove(subResult.size() - 1);
        }
      }
    }

    class PermutationWithDuplicates implements Permutation {

      @Override
      public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
          return results;
        }
        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new ArrayList<>(), results);
        return results;
      }

      private void dfs(int[] nums, boolean[] used, List<Integer> subResult,
          List<List<Integer>> results) {
        if (subResult.size() == nums.length) {
          results.add(new ArrayList<>(subResult));
          return;
        }
        for (int i = 0; i < nums.length; i++) {
          if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;
          }
          used[i] = true;
          subResult.add(nums[i]);
          dfs(nums, used, subResult, results);
          used[i] = false;
          subResult.remove(subResult.size() - 1);
        }
      }
    }
  }

  interface Subset {

    List<List<Integer>> subsets(int[] nums);

    class SubsetsWithDuplicates implements Subset {

      @Override
      public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
          return results;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), results);
        return results;
      }

      private void dfs(int[] nums, int start, List<Integer> subResult,
          List<List<Integer>> results) {
        results.add(new ArrayList<>(subResult));
        for (int i = start; i < nums.length; i++) {
          if (i > start && nums[i] == nums[i - 1]) {
            continue; // Skip duplicates
          }
          subResult.add(nums[i]);
          dfs(nums, i + 1, subResult, results);
          subResult.remove(subResult.size() - 1);
        }
      }
    }
  }

  interface CombinationSum {

    List<List<Integer>> combinationSum(int[] nums, int target);

    class CombinationSumWithDuplicates implements CombinationSum {

      @Override
      public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
          return results;
        }
        Arrays.sort(nums);
        dfs(nums, 0, target, new ArrayList<>(), results);
        return results;
      }

      private void dfs(int[] nums, int start, int remain, List<Integer> subResult,
          List<List<Integer>> results) {
        if (remain < 0) {
          return;
        } else if (remain == 0) {
          results.add(new ArrayList<>(subResult));
          return;
        }
        for (int i = start; i < nums.length; i++) {
          if (i > start && nums[i] == nums[i - 1]) {
            continue; // Skip duplicates
          }
          subResult.add(nums[i]);
          dfs(nums, i + 1, remain - nums[i], subResult, results);
          subResult.remove(subResult.size() - 1);
        }
      }
    }
  }

  static Permutation getPermutationInstance() {
    return new PermutationWithDuplicates();
  }

  static Subset getSubsetInstance() {
    return new SubsetsWithDuplicates();
  }

  static CombinationSum getCombinationSumInstance() {
    return new CombinationSumWithDuplicates();
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 2, 3};

    List<List<Integer>> list1 = getPermutationInstance().permute(nums);
    System.out.println(list1.toString());

    List<List<Integer>> list2 = getSubsetInstance().subsets(nums);
    System.out.println(list2.toString());

    List<List<Integer>> list3 = getCombinationSumInstance().combinationSum(nums, 4);
    System.out.println(list3.toString());
  }

}