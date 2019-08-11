package leetcode.p351to400;

import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Different from the previous question where weight is increasing from root to leaf,
 * now the weight is defined from bottom up. i.e., the leaf level integers have weight 1,
 * and the root level integers have the largest weight.
 *
 * @see leetcode.p301to350.LeetCode339NestedListWeightSum
 * <p>
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * <p>
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 * <p>
 * Same as: Lintcode 905. Nested List Weight Sum II
 */
public class LeetCode364NestedListWeightSumII {

    private interface NestedInteger {
        // @return true if this NestedInteger holds a single integer,
        // rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds,
        // if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds,
        // if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    private interface Method {
        int depthSumInverse(List<NestedInteger> nestedList);
    }

    private static class DFS implements Method {

        @Override
        public int depthSumInverse(List<NestedInteger> nestedList) {
            int maxDepth = getDepth(nestedList, 0);
            return depthSum(nestedList, maxDepth);
        }

        private int getDepth(List<NestedInteger> nestedList, int depth) {
            if (nestedList == null || nestedList.isEmpty()) {
                return depth;
            }
            int max = depth + 1;
            for (NestedInteger elem : nestedList) {
                if (!elem.isInteger()) {
                    max = Math.max(max, getDepth(elem.getList(), depth + 1));
                }
            }
            return max;
        }

        private int depthSum(List<NestedInteger> nestedList, int height) {
            if (nestedList == null || nestedList.isEmpty()) {
                return 0;
            }
            int sum = 0;
            for (NestedInteger elem : nestedList) {
                if (elem.isInteger()) {
                    sum += elem.getInteger() * height;
                } else {
                    sum += depthSum(elem.getList(), height - 1);
                }
            }
            return sum;
        }
    }
}
