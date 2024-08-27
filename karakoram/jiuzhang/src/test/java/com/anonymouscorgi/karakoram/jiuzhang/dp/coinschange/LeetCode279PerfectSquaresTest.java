package com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange.LeetCode279PerfectSquares.Algorithm;
import com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange.LeetCode279PerfectSquares.SubmittedMethod1;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode279PerfectSquaresTest {

  static Stream<Arguments> factoryMethodWithArguments() {
    return Stream.of(
        arguments(/* n= */ "12", /* expected= */ 3),
        arguments( /* n= */ "13", /* expected= */ 2)
    );
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void numSquares(int n, int expected) {
    Algorithm algorithm = new SubmittedMethod1();

    assertThat(algorithm.numSquares(n)).isEqualTo(expected);
  }
}