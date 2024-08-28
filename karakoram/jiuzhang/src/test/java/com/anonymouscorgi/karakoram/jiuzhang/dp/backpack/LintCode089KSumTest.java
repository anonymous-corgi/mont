package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.LintCode089KSum.Algorithm;
import com.anonymouscorgi.karakoram.jiuzhang.dp.backpack.LintCode089KSum.Method;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode089KSumTest {

  private static Stream<Arguments> generateTestParametersStream()  {
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
    Algorithm algorithms = new Method();

    assertThat(algorithms.kSum(A, k, target)).isEqualTo(expected);
  }
}