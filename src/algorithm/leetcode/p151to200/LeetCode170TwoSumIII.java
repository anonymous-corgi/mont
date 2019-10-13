package algorithm.leetcode.p151to200;

import java.util.HashMap;
import java.util.Map;

/**
 * 170. Two Sum III - Data structure design
 * Easy
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * Example 1:
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 *
 * Example 2:
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */
public class LeetCode170TwoSumIII {

    private static abstract class TwoSum {

        /**
         * Add the number to an internal data structure..
         */
        public abstract void add(int number);

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public abstract boolean find(int value);
    }

    private static final class TwoSumImpl extends TwoSum {

        private final Map<Integer, Integer> item = new HashMap<>();

        @Override
        public void add(int number) {
            item.compute(number, (k, v) -> (v == null ? 1 : v + 1));
        }

        @Override
        public boolean find(int value) {
            for (Integer each : item.keySet()) {
                if ((value == (2 * each)) && (item.get(each) > 1)) {
                    return true;
                } else if (item.containsKey(value - each)) {
                    return true;
                }
            }
            return false;
        }
    }
}
