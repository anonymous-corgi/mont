package com.anonymouscorgi.karakoram.kb1600

import com.anonymouscorgi.karakoram.base.TreeNode
import com.anonymouscorgi.karakoram.base.util.TreeNodeUtil
import com.anonymouscorgi.karakoram.kb1600.LeetCode1609EvenOddTree.*
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.provider.Arguments.arguments

import java.util.stream.Stream
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LeetCode1609EvenOddTreeTest {

  companion object {

    @JvmStatic
    fun generateTestParametersStream(): Stream<Arguments> =
      Stream.of(
        arguments(
          /* root */ TreeNodeUtil.fromIntList(listOf(1,10,4,3,null,7,9,12,8,6,null,null,2)),
          /* expected */ true
        ),
        arguments(
          /* root */ TreeNodeUtil.fromIntList(listOf(5,4,2,3,3,7)),
          /* expected */ false
        ),
        arguments(
          /* root */ TreeNodeUtil.fromIntList(listOf(5,9,1,3,5,7)),
          /* expected */ false
        )
      )
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  fun isEvenOddTree(root: TreeNode, expected: Boolean) {
    assertThat(DFS.isEvenOddTree(root)).isEqualTo(expected)
  }
}