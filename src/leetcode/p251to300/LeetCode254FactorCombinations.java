package leetcode.p251to300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 254. Factor Combinations
 * Medium
 * <p>
 * Numbers can be regarded as product of its factors. For example,
 * <p>
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * <p>
 * Note:
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * <p>
 * Example 1:
 * Input: 1
 * Output: []
 * <p>
 * Example 2:
 * Input: 37
 * Output:[]
 * <p>
 * Example 3:
 * Input: 12
 * Output:
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * <p>
 * Example 4:
 * Input: 32
 * Output:
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 */
@SuppressWarnings("unused")
public class LeetCode254FactorCombinations {

    private interface Method {
        List<List<Integer>> getFactors(int n);
    }

    private static final class Memorization implements Method {

        public List<List<Integer>> getFactors(int n) {
            return getFactors(n, new HashMap<>());
        }

        private List<List<Integer>> getFactors(int n, Map<Integer, List<List<Integer>>> cache) {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    List<List<Integer>> remainderFactors = getFactors(n / i, cache);
                    res.add(Arrays.asList(i, n / i));
                    for (List<Integer> factors : remainderFactors) {
                        // Important step to avoid duplication. make all the results ascending.
                        if (factors.get(0) >= i) {
                            List<Integer> neo = new ArrayList<>();
                            neo.add(i);
                            neo.addAll(factors);
                            res.add(neo);
                        }
                    }
                }
            }
            cache.put(n, res);
            return res;
        }
    }
}