package com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange.LeetCode091DecodeWays.Algorithm;
import com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange.LeetCode091DecodeWays.SubmittedBackward;
import com.anonymouscorgi.karakoram.jiuzhang.dp.coinschange.LeetCode091DecodeWays.Forward;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode091DecodeWaysTest {

  static Stream<Arguments> factoryMethodWithArguments() {
    return Stream.of(
        arguments(/* s= */ "20",/* expected= */ 1),
        arguments(/* s= */ "12",/* expected= */ 2),
        arguments(/* s= */ "226",/* expected= */ 3),
        arguments(/* s= */ "06",/* expected= */ 0)
    );
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void numDecodings_method1(String s, int expected) {
    Algorithm algorithm = new SubmittedBackward();

    assertThat(algorithm.numDecodings(s)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void numDecodings_method2(String s, int expected) {
    Algorithm algorithm = new Forward();

    assertThat(algorithm.numDecodings(s)).isEqualTo(expected);
  }
}