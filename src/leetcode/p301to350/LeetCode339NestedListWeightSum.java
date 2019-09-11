package leetcode.p301to350;

import basicclass.NestedInteger;

import java.util.List;

/**
 * LeetCode 339. Nested List Weight Sum
 * Easy
 * <p>
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 *
 * Same as: LintCode 551. Nested List Weight Sum
 */
@SuppressWarnings("unused")
public class LeetCode339NestedListWeightSum {

    private interface Method {
        int depthSum(List<NestedInteger> nestedList);
    }

    private static class DFS implements Method {

        @Override
        public int depthSum(List<NestedInteger> nestedList) {
            return depthSum(nestedList, 1);
        }

        private int depthSum(List<NestedInteger> nestedList, int depth) {
            if (nestedList == null || nestedList.isEmpty()) {
                return 0;
            }
            int sum = 0;
            for (NestedInteger elem : nestedList) {
                if (elem.isInteger()) {
                    sum += elem.getInteger() * depth;
                } else {
                    sum += depthSum(elem.getList(), depth + 1);
                }
            }
            return sum;
        }
    }
}
