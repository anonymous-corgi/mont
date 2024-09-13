package com.anonymouscorgi.karakoram.kb0050;

import static com.anonymouscorgi.karakoram.kb0050.LintCode089KSum.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode089KSumTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(arguments(
        /* A= */ new int[]{1, 2, 3, 4},
        /* k= */ 2,
        /* target= */ 5,
        /* expected= */ 2)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void kSum_method1(int[] A, int k, int target, int expected) {
    assertThat(Method.kSum(A, k, target)).isEqualTo(expected);
  }
}