package leetcode.p201to250;

import basicclass.TreeNode;

public class LeetCode236LowestCommonAncestorOfABinaryTree {

    private interface Method {
        TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
    }

    private static final class BackTracking implements Method {

        @Override
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode[] result = new TreeNode[1];
            lowestCommonAncestor(root, p, q, result);
            return result[0];
        }

        private int lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, TreeNode[] result) {
            if (root == null || result[0] != null) {
                return 0;
            }
            int res = 0;
            res += root.val == p.val ? 1 : 0;
            res += root.val == q.val ? 2 : 0;
            int lRes = lowestCommonAncestor(root.left, p, q, result);
            if (lRes == 3) {
                return 3;
            }
            if ((res += lRes) == 3) {
                result[0] = root;
                return 3;
            }
            int rRes = lowestCommonAncestor(root.right, p, q, result);
            if (rRes == 3) {
                return 3;
            }
            if ((res += rRes) == 3) {
                result[0] = root;
                return 3;
            }
            return res;
        }
    }
}
