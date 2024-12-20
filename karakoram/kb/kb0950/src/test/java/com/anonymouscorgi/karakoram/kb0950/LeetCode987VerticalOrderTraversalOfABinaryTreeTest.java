package com.anonymouscorgi.karakoram.kb0950;

import static com.anonymouscorgi.karakoram.kb0950.LeetCode987VerticalOrderTraversalOfABinaryTree.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.base.TreeNode;
import com.anonymouscorgi.karakoram.base.util.TreeNodeUtil;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode987VerticalOrderTraversalOfABinaryTreeTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* root= */ TreeNodeUtil.deserialize("[3,9,20,null,null,15,7]"),
            /* expected= */ List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7))),
        arguments(
            /* root= */ TreeNodeUtil.deserialize("[3,9,8,4,0,1,7]"),
            /* expected= */
            List.of(List.of(4), List.of(9), List.of(3, 0, 1), List.of(8), List.of(7))),
        arguments(
            /* root= */TreeNodeUtil.deserialize(
                "[1,2,3,4,10,9,11,null,5,null,null,null,null,null,null,null,6]"),
            /* expected= */
            List.of(List.of(4), List.of(2, 5), List.of(1, 10, 9, 6), List.of(3), List.of(11))),
        arguments(
            /* root= */TreeNodeUtil.deserialize(
                "[3,9,20,null,null,15,7]"),
            /* expected= */
            List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7))),
        arguments(
            /* root= */TreeNodeUtil.deserialize(
                "[1,2,3,4,5,6,7]"),
            /* expected= */
            List.of(List.of(4), List.of(2), List.of(1, 5, 6), List.of(3), List.of(7))),
        arguments(
            /* root= */TreeNodeUtil.deserialize(
                "[1,2,3,4,6,5,7]"),
            /* expected= */
            List.of(List.of(4), List.of(2), List.of(1, 5, 6), List.of(3), List.of(7)))
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void verticalTraversal(TreeNode root, List<List<Integer>> expected) {
    assertThat(BFS.verticalTraversal(root)).isEqualTo(expected);
  }
}