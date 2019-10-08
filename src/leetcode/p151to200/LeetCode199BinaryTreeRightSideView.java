package leetcode.p151to200;

import java.util.ArrayList;
import java.util.List;

import basicclass.TreeNode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode199BinaryTreeRightSideView {

    private interface Method {
        List<Integer> rightSideView(TreeNode root);
    }

    private static final class DFS implements Method {

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            rightSideView(root, 0, res);
            return res;
        }

        private void rightSideView(TreeNode root, int depth, List<Integer> res) {
            if (root == null) {
                return;
            }
            if (depth == res.size()) {
                res.add(root.val);
            }
            rightSideView(root.right, depth + 1, res);
            rightSideView(root.left, depth + 1, res);
        }
    }
}