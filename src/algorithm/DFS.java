package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class DFS {

    private interface Permutation {

        List<List<Integer>> permute(int[] nums);

        abstract class AbstractPermutation implements Permutation {

            @Override
            public List<List<Integer>> permute(int[] nums) {
                if (nums == null || nums.length == 0) {
                    return Collections.emptyList();
                }
                Arrays.sort(nums);
                List<List<Integer>> result = new ArrayList<>();
                dfs(nums, new boolean[nums.length], new ArrayList<>(), result);
                return result;
            }

            abstract void dfs(int[] nums, boolean[] used, List<Integer> builder, List<List<Integer>> result);
        }

        class WithoutDuplicates extends AbstractPermutation {

            @Override
            void dfs(int[] nums, boolean[] used, List<Integer> builder, List<List<Integer>> result) {
                if (builder.size() == nums.length) {
                    result.add(new ArrayList<>(builder));
                    return;
                }

                for (int i = 0; i < nums.length; i++) {
                    if (used[i]) {
                        continue;
                    }
                    used[i] = true;
                    builder.add(nums[i]);
                    dfs(nums, used, builder, result);
                    used[i] = false;
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithDuplicates extends AbstractPermutation {

            @Override
            void dfs(int[] nums, boolean[] used, List<Integer> builder, List<List<Integer>> result) {
                if (builder.size() == nums.length) {
                    result.add(new ArrayList<>(builder));
                    return;
                }
                for (int i = 0; i < nums.length; i++) {
                    if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                        continue;
                    }
                    used[i] = true;
                    builder.add(nums[i]);
                    dfs(nums, used, builder, result);
                    used[i] = false;
                    builder.remove(builder.size() - 1);
                }
            }
        }
    }

    private interface Subsets {

        List<List<Integer>> subsets(int[] nums);

        abstract class AbstractSubsets implements Subsets {

            @Override
            public List<List<Integer>> subsets(int[] nums) {
                if (nums == null || nums.length == 0) {
                    return Collections.emptyList();
                }
                Arrays.sort(nums);
                List<List<Integer>> result = new ArrayList<>();
                dfs(nums, 0, new ArrayList<>(), result);
                return result;
            }

            abstract void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> result);
        }

        class WithoutDuplicates0 extends AbstractSubsets {

            @Override
            void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> result) {
                if (start == nums.length) {
                    result.add(new ArrayList<>(builder));
                    return;
                }
                dfs(nums, start + 1, builder, result);
                builder.add(nums[start]);
                dfs(nums, start + 1, builder, result);
                builder.remove(builder.size() - 1);
            }
        }

        class WithoutDuplicates1 extends AbstractSubsets {

            @Override
            void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> result) {
                result.add(new ArrayList<>(builder));
                for (int i = start; i < nums.length; i++) {
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, result);
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithoutDuplicates2 extends AbstractSubsets {

            // It works the same as the above.
            @Override
            void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> result) {
                if (start >= nums.length) {
                    result.add(new ArrayList<>(builder));
                    return;
                }
                // Pick nothing from nums and add this to the results.
                dfs(nums, nums.length, builder, result);
                // Pick one from nums.
                for (int i = start; i < nums.length; i++) {
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, result);
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithDuplicates extends AbstractSubsets {

            /**
             * Use builder as a prefix to create a subset of numbers, which is from nums[start],...,nums[nums.length].
             */
            @Override
            void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> result) {
                result.add(new ArrayList<>(builder));
                for (int i = start; i < nums.length; i++) {
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue; // Skip duplicates
                    }
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, result);
                    builder.remove(builder.size() - 1);
                }
            }
        }
    }

    private interface CombinationSum {

        List<List<Integer>> combinationSum(int[] nums, int target);

        abstract class AbstractCombinationSum implements CombinationSum {

            @Override
            public List<List<Integer>> combinationSum(int[] nums, int target) {
                if (nums == null || nums.length == 0) {
                    return Collections.emptyList();
                }
                Arrays.sort(nums);
                List<List<Integer>> result = new ArrayList<>();
                dfs(nums, 0, target, new ArrayList<>(), result);
                return result;
            }

            abstract void dfs(int[] nums, int start, int target, List<Integer> builder, List<List<Integer>> result);
        }

        // 039. Combination Sum
        class ReusableWithoutDuplicates extends AbstractCombinationSum {

            @Override
            void dfs(int[] nums, int start, int target, List<Integer> builder, List<List<Integer>> result) {
                if (target < 0) {
                    return;
                } else if (target == 0) {
                    result.add(new ArrayList<>(builder));
                    return;
                }
                for (int i = start; i < nums.length; i++) {
                    builder.add(nums[i]);
                    dfs(nums, i, target - nums[i], builder, result);
                    builder.remove(builder.size() - 1);
                }
            }
        }

        // 040. Combination Sum II
        class WithDuplicates extends AbstractCombinationSum {

            @Override
            void dfs(int[] nums, int start, int target, List<Integer> builder, List<List<Integer>> result) {
                if (target < 0) {
                    return;
                } else if (target == 0) {
                    result.add(new ArrayList<>(builder));
                    return;
                }
                for (int i = start; i < nums.length; i++) {
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue; // Skip duplicates
                    }
                    builder.add(nums[i]);
                    dfs(nums, i + 1, target - nums[i], builder, result);
                    builder.remove(builder.size() - 1);
                }
            }
        }
    }

    private static Permutation getPermutationInstance() {
        return new Permutation.WithDuplicates();
    }

    private static Subsets getSubsetInstance() {
        return new Subsets.WithDuplicates();
    }

    private static CombinationSum getCombinationSumInstance() {
        return new CombinationSum.WithDuplicates();
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