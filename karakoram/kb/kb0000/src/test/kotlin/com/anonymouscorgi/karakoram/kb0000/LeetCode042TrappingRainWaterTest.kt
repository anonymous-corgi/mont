package com.anonymouscorgi.karakoram.kb0000

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.provider.Arguments.arguments

import java.util.stream.Stream
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import com.anonymouscorgi.karakoram.kb0000.LeetCode042TrappingRainWater.METHOD

class LeetCode042TrappingRainWaterTest {

  companion object {

    @JvmStatic
    fun generateTestParametersStream(): Stream<Arguments> {
      return Stream.of<Arguments>(
        arguments(
          /* height= */ intArrayOf(1, 0, 1),
          /* expected= */ 1
        ),
        arguments(
          /* height= */ intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1),
          /* expected= */ 6
        ),
      )
    }
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  fun trap(height: IntArray, expected: Int) {
    assertThat(METHOD.trap(height)).isEqualTo(expected);
  }
}