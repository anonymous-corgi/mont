package com.anonymouscorgi.karakoram.kb0050;

import static com.anonymouscorgi.karakoram.kb0050.LintCode075FindPeakElement.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode075FindPeakElementTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* nums= */ new int[]{1, 2, 3},
            /* expected= */ 2),
        arguments(
            /* nums= */ new int[]{3, 2, 1},
            /* expected= */ 0)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void findPeak_Method(int[] nums, int expected) {
    assertThat(Method.findPeak(nums)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void findPeak_RecursiveMethod(int[] nums, int expected) {
    assertThat(ReCursive_Method.findPeak(nums)).isEqualTo(expected);
  }
}