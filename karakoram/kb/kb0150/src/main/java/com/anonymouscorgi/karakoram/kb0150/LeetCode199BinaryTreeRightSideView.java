package com.anonymouscorgi.karakoram.kb0150;

import com.anonymouscorgi.karakoram.annotation.Accepted;
import com.anonymouscorgi.karakoram.base.TreeNode;
import java.util.ArrayList;
import java.util.List;

interface LeetCode199BinaryTreeRightSideView {

  List<Integer> rightSideView(TreeNode root);

  @Accepted
  LeetCode199BinaryTreeRightSideView Method = new LeetCode199BinaryTreeRightSideView() {
    @Override
    public List<Integer> rightSideView(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      rightSideView(root, 0, result);
      return result;
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
  };
}
