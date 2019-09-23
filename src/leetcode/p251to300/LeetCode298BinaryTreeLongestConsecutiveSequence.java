package leetcode.p251to300;

import basicclass.TreeNode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Medium
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 * Input:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 *
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * Example 2:
 * Input:
 *
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 *
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class LeetCode298BinaryTreeLongestConsecutiveSequence {

    private interface Method {
        int longestConsecutive(TreeNode root);
    }

    private static final class BackTracking implements Method {

        public int longestConsecutive(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] longest = new int[1];
            dfs(root, longest);
            return longest[0];
        }

        private int dfs(TreeNode root, int[] longest) {
            int result = 1;
            if (root.left != null) {
                int left = dfs(root.left, longest);
                if (root.left.val == root.val + 1) {
                    result = Math.max(result, left + 1);
                }
            }
            if (root.right != null) {
                int right = dfs(root.right, longest);
                if (root.right.val == root.val + 1) {
                    result = Math.max(result, right + 1);
                }
            }
            longest[0] = Math.max(longest[0], result);
            return result;
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
        TreeNode root = TreeNode.deserialize("[1,null,3,2,4,null,null,null,5]");
        test(root, 3);
    }
}
