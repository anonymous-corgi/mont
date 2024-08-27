package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.BackPackIII.Algorithm;
import com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.BackPackIII.Method1;
import com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.BackPackIII.Method2;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BackPackIIITest {

  static Stream<Arguments> factoryMethodWithArguments() {
    return Stream.of(arguments(
        /* capacity= */ 30,
        /* weights= */ new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
        /* values= */ new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30},
        /* expected= */ 90));
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void backPackIII_method1(int capacity, int[] weights, int[] values, int expected) {
    Algorithm algorithm = new Method1();

    assertThat(algorithm.backPackIII(capacity, weights, values)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void backPackIII_method2(int capacity, int[] weights, int[] values, int expected) {
    Algorithm algorithm = new Method2();

    assertThat(algorithm.backPackIII(capacity, weights, values)).isEqualTo(expected);
  }
}