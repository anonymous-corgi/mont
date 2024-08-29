package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

import static com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.BackPackIII.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BackPackIIITest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(arguments(
        /* capacity= */ 30,
        /* weights= */ new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
        /* values= */ new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30},
        /* expected= */ 90));
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void backPackIII_method1(int capacity, int[] weights, int[] values, int expected) {
    Algorithm algorithm = Method1;

    assertThat(algorithm.backPackIII(capacity, weights, values)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void backPackIII_method2(int capacity, int[] weights, int[] values, int expected) {
    Algorithm algorithm = Method2;

    assertThat(algorithm.backPackIII(capacity, weights, values)).isEqualTo(expected);
  }
}