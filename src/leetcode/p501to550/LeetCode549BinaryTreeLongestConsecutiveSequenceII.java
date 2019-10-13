package leetcode.p501to550;

import org.junit.Test;

import algorithm.base.TreeNode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 549. Binary Tree Longest Consecutive Sequence II
 * Medium
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing.
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
 * but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order,
 * where not necessarily be parent-child order.
 *
 * Example 1:
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 * Example 2:
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 */
public class LeetCode549BinaryTreeLongestConsecutiveSequenceII {

    private interface Method {
        int longestConsecutive(TreeNode root);
    }

    private static final class BackTracking implements Method {

        private static final class Pair {
            int asc = 1;
            int des = 1;
        }

        public int longestConsecutive(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] longest = new int[1];
            dfs(root, longest);
            return longest[0];
        }

        private Pair dfs(TreeNode root, int[] longest) {
            Pair pair = new Pair();
            if (root.left != null) {
                Pair left = dfs(root.left, longest);
                if (root.left.val == root.val + 1) {
                    pair.asc = Math.max(pair.asc, left.asc + 1);
                } else if (root.left.val == root.val - 1) {
                    pair.des = Math.max(pair.des, left.des + 1);
                }
            }
            if (root.right != null) {
                Pair right = dfs(root.right, longest);
                if (root.right.val == root.val + 1) {
                    pair.asc = Math.max(pair.asc, right.asc + 1);
                } else if (root.right.val == root.val - 1) {
                    pair.des = Math.max(pair.des, right.des + 1);
                }
            }
            longest[0] = Math.max(longest[0], (pair.asc + pair.des - 1));
            return pair;
        }
    }

    private static Method getMethod() {
        return new BackTracking();
    }

    private void test(TreeNode root, int expected) {
        int actual = getMethod().longestConsecutive(root);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        TreeNode root = TreeNode.deserialize("[1,2,3,4]");
        test(root, 2);
    }

    @Test
    public void testcase2() {
        TreeNode root = TreeNode.deserialize("[2,1,3,null,null,4]");
        test(root, 4);
    }
}