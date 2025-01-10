package com.anonymouscorgi.karakoram.kb1000;

import static com.anonymouscorgi.karakoram.kb1000.LeetCode1004MaxConsecutiveOnesIII.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode1004MaxConsecutiveOnesIIITest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* nums= */ new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0},
            /* k= */ 2,
            /* expected= */ 6),
        arguments(
            /* nums= */ new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            /* k= */ 3,
            /* expected= */ 10),
        arguments(
            /* nums= */ new int[]{0, 0, 1, 1},
            /* k= */ 1,
            /* expected= */ 3),
        arguments(
            /* nums= */ new int[]{0},
            /* k= */ 1,
            /* expected= */ 1)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void longestOnes(int[] nums, int k, int expected) {
    assertThat(METHOD.longestOnes(nums, k)).isEqualTo(expected);
  }
}