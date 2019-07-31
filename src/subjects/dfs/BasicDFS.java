package subjects.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import subjects.dfs.BasicDFS.CombinationSum.CombinationSumWithDuplicates;
import subjects.dfs.BasicDFS.Subsets.WithDuplicates;

@SuppressWarnings("unused")
public class BasicDFS {

    interface Permutation {

        List<List<Integer>> permute(int[] nums);

        class WithoutDuplicates implements Permutation {

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

            private void dfs(int[] nums, boolean[] used, List<Integer> builder, List<List<Integer>> results) {
                if (builder.size() == nums.length) {
                    results.add(new ArrayList<>(builder));
                    return;
                }

                for (int i = 0; i < nums.length; i++) {
                    if (used[i]) {
                        continue;
                    }
                    used[i] = true;
                    builder.add(nums[i]);
                    dfs(nums, used, builder, results);
                    used[i] = false;
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithDuplicates implements Permutation {

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

            private void dfs(int[] nums, boolean[] used, List<Integer> builder, List<List<Integer>> results) {
                if (builder.size() == nums.length) {
                    results.add(new ArrayList<>(builder));
                    return;
                }
                for (int i = 0; i < nums.length; i++) {
                    if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                        continue;
                    }
                    used[i] = true;
                    builder.add(nums[i]);
                    dfs(nums, used, builder, results);
                    used[i] = false;
                    builder.remove(builder.size() - 1);
                }
            }
        }
    }

    interface Subsets {

        List<List<Integer>> subsets(int[] nums);

        class WithoutDuplicates0 implements Subsets {

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

            private void dfs(int[] nums, int index, List<Integer> builder, List<List<Integer>> results) {
                if (index == nums.length) {
                    results.add(new ArrayList<>(builder));
                    return;
                }
                dfs(nums, index + 1, builder, results);
                builder.add(nums[index]);
                dfs(nums, index + 1, builder, results);
                builder.remove(builder.size() - 1);
            }
        }

        class WithoutDuplicates1 implements Subsets {

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

            private void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> results) {
                results.add(new ArrayList<>(builder));
                for (int i = start; i < nums.length; i++) {
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, results);
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithoutDuplicates2 implements Subsets {

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

            // It works the same as the above.
            private void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> results) {
                if (start >= nums.length) {
                    results.add(new ArrayList<>(builder));
                    return;
                }
                // Pick nothing from nums and add this to the results.
                dfs(nums, nums.length, builder, results);
                // Pick one from nums.
                for (int i = start; i < nums.length; i++) {
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, results);
                    builder.remove(builder.size() - 1);
                }
            }
        }

        class WithDuplicates implements Subsets {

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

            /**
             * Use builder as a prefix to create a subset of numbers, which is from nums[start],...,nums[nums.length].
             */
            private void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> results) {
                results.add(new ArrayList<>(builder));
                for (int i = start; i < nums.length; i++) {
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue; // Skip duplicates
                    }
                    builder.add(nums[i]);
                    dfs(nums, i + 1, builder, results);
                    builder.remove(builder.size() - 1);
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

            private void dfs(int[] nums, int start, int remain, List<Integer> builder, List<List<Integer>> results) {
                if (remain < 0) {
                    return;
                } else if (remain == 0) {
                    results.add(new ArrayList<>(builder));
                    return;
                }
                for (int i = start; i < nums.length; i++) {
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue; // Skip duplicates
                    }
                    builder.add(nums[i]);
                    dfs(nums, i + 1, remain - nums[i], builder, results);
                    builder.remove(builder.size() - 1);
                }
            }
        }
    }

    private static Permutation getPermutationInstance() {
        return new Permutation.WithDuplicates();
    }

    private static Subsets getSubsetInstance() {
        return new WithDuplicates();
    }

    private static CombinationSum getCombinationSumInstance() {
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