package leetcode.p551to600;

import java.util.HashMap;
import java.util.Map;

/**
 * Similar to {@link leetcode.p951to1000.LeetCode974SubarraySumsDivisibleByK}
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class LeetCode560SubarraySumEqualsK {

    private interface Method {
        int subarraySum(int[] nums, int k);
    }

    private static final class ProcessingMap implements Method {

        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int prefix = 0;
            int res = 0;
            Map<Integer, Integer> sumsCount = new HashMap<>();
            sumsCount.put(0, 1);
            for (int num : nums) {
                prefix += num;
                res += sumsCount.getOrDefault(prefix - k, 0);
                sumsCount.compute(prefix, (key, value) -> value != null ? value + 1 : 1);
            }
            return res;
        }
    }
}
