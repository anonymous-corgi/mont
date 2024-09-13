package com.anonymouscorgi.karakoram.kb0250;

import static com.anonymouscorgi.karakoram.kb0250.LeetCode279PerfectSquares.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode279PerfectSquaresTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(/* n= */ "12", /* expected= */ 3),
        arguments( /* n= */ "13", /* expected= */ 2)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void numSquares(int n, int expected) {
    assertThat(SubmittedMethod1.numSquares(n)).isEqualTo(expected);
  }
}