package algorithm.leetcode.p051to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode090SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), results);
        return results;
    }

    private void dfs(int[] nums, int start, List<Integer> builder, List<List<Integer>> results) {
        results.add(builder);
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            List<Integer> result = new ArrayList<>(builder);
            result.add(nums[i]);
            dfs(nums, i + 1, result, results);
        }
    }
}
