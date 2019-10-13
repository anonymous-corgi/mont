package lintcode.p201to250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import algorithm.base.Interval;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * 205. Interval Minimum Number
 * Medium
 *
 * Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list.
 * Each query has two integers [start, end].
 * For each query, calculate the minimum number between index start and end in the given array,
 * return the result list.
 *
 * We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
 *
 * Have you met this question in a real interview?
 * Example
 * Example 1:
 * Input : array: [1,2,7,8,5], queries :[(1,2),(0,4),(2,4)].
 * Output: [2,1,5]
 *
 * Example 2:
 * Input : array: [4,5,7,1], queries :[(1,2),(1,3)].
 * Output: [5,1]
 *
 * Challenge
 * O(logN) time for each query
 */
@RunWith(Parameterized.class)
public class LintCode205IntervalMinimumNumber {
    private final int[] A;
    private final List<Interval> queries;
    private final List<Integer> expected;

    public LintCode205IntervalMinimumNumber(int[] A, List<Interval> queries, List<Integer> expected) {
        this.A = A;
        this.queries = queries;
        this.expected = expected;
    }

    private interface Method {
        List<Integer> intervalMinNumber(int[] A, List<Interval> queries);
    }

    private static final class SegmentTree implements Method {

        private static final class SGTree {

            private static final class Node {
                int begin;
                int end;
                int min;
                Node left;
                Node right;

                Node(int begin, int end) {
                    this.begin = begin;
                    this.end = end;
                }
            }

            private Node root;

            SGTree(int[] nums) {
                this.root = buildTree(nums, 0, nums.length - 1);
            }

            private Node buildTree(int[] nums, int begin, int end) {
                if (begin > end) {
                    return null;
                }
                Node cursor = new Node(begin, end);
                if (begin == end) {
                    cursor.min = nums[begin];
                } else {
                    int mid = begin + ((end - begin) / 2);
                    cursor.left = buildTree(nums, begin, mid);
                    cursor.right = buildTree(nums, mid + 1, end);
                    cursor.min = Math.min(cursor.left.min, cursor.right.min);
                }
                return cursor;
            }

            private int findMin(int begin, int end) {
                return findMin(root, begin, end);
            }

            private int findMin(Node node, int begin, int end) {
                if (node.begin == begin && node.end == end) {
                    return node.min;
                }
                int min = Integer.MAX_VALUE;
                if (begin <= node.left.end) {
                    min = Math.min(min, findMin(node.left, begin, node.left.end));
                }
                if (end >= node.right.begin) {
                    min = Math.min(min, findMin(node.right, node.right.begin, end));
                }
                return min;
            }
        }

        public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
            SGTree tree = new SGTree(A);
            List<Integer> res = new ArrayList<>(queries.size());
            for (Interval query : queries) {
                res.add(tree.findMin(query.start, query.end));
            }
            return res;
        }
    }

    private static Method getMethod() {
        return new SegmentTree();
    }

    private void test(int[] A, List<Interval> queries, List<Integer> expected) {
        List<Integer> actual = getMethod().intervalMinNumber(A, queries);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testcase() {
        test(A, queries, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[]{1, 2, 7, 8, 5}, Arrays.asList(new Interval(1, 2), new Interval(0, 4), new Interval(2, 4)), Arrays.asList(2, 1, 5)}
        };
    }
}
