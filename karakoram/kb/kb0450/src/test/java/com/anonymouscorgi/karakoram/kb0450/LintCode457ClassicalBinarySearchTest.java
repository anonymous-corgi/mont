package com.anonymouscorgi.karakoram.kb0450;

import static com.anonymouscorgi.karakoram.kb0450.LintCode457ClassicalBinarySearch.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode457ClassicalBinarySearchTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* nums= */ new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
            /* target= */ 4,
            /* expected= */ 3)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void findPosition_methoe(int[] nums, int target, int expected) {
    assertThat(Method.findPosition(nums, target)).isEqualTo(expected);
  }
}