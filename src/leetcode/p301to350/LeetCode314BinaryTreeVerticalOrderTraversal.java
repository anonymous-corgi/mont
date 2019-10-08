package leetcode.p301to350;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import basicclass.TreeNode;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Medium
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples 1:
 * Input: [3,9,20,null,null,15,7]
 *
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 *
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 *
 * Examples 2:
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 *
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 *
 * Examples 3:
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 */
public class LeetCode314BinaryTreeVerticalOrderTraversal {

    private interface Method {
        List<List<Integer>> verticalOrder(TreeNode root);
    }

    private static final class BFS implements Method {

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            int[] bias = new int[2];
            getBias(root, 0, bias);
            for (int i = 0, len = bias[1] - bias[0] + 1; i < len; i++) {
                results.add(new ArrayList<>());
            }

            Queue<Integer> posList = new ArrayDeque<>();
            Queue<TreeNode> taskList = new ArrayDeque<>();
            taskList.offer(root);
            posList.offer(-bias[0]);
            while (!taskList.isEmpty()) {
                TreeNode cursor = taskList.poll();
                int pos = posList.poll();
                results.get(pos).add(cursor.val);
                if (cursor.left != null) {
                    taskList.offer(cursor.left);
                    posList.offer(pos - 1);
                }
                if (cursor.right != null) {
                    taskList.offer(cursor.right);
                    posList.offer(pos + 1);
                }
            }
            return results;
        }

        private void getBias(TreeNode root, int pos, int[] bias) {
            if (root == null) {
                return;
            }
            bias[0] = Math.min(bias[0], pos);
            bias[1] = Math.max(bias[1], pos);
            getBias(root.left, pos - 1, bias);
            getBias(root.right, pos + 1, bias);
        }
    }
}
