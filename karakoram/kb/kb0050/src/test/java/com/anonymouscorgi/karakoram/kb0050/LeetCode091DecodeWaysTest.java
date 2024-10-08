package com.anonymouscorgi.karakoram.kb0050;

import static com.anonymouscorgi.karakoram.kb0050.LeetCode091DecodeWays.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode091DecodeWaysTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(/* s= */ "20",/* expected= */ 1),
        arguments(/* s= */ "12",/* expected= */ 2),
        arguments(/* s= */ "226",/* expected= */ 3),
        arguments(/* s= */ "06",/* expected= */ 0)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void numDecodings_method1(String s, int expected) {
    assertThat(SubmittedBackward.numDecodings(s)).isEqualTo(expected);
  }
}