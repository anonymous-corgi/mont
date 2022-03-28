package algorithm.leetcode.p001to050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class LeetCode015ThreeSum {

    private interface Method {
        List<List<Integer>> threeSum(int[] nums);
    }

    private static class Normal implements Method {

        @Override
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Collections.emptyList();
            }
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = nums.length - 1; i >= 2; ) {
                int left = 0;
                int right = i - 1;
                int target = -nums[i];
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        left++;
                        right--;
                    }
                    while (left != 0 && left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (right != i - 1 && left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
                while (--i >= 2 && nums[i] == nums[i + 1]) ;
            }
            return res;
        }
    }
}
