package com.anonymouscorgi.karakoram.jiuzhang.dp;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.jiuzhang.dp.LeetCode152MaximumProductSubarray.Algorithm;
import com.anonymouscorgi.karakoram.jiuzhang.dp.LeetCode152MaximumProductSubarray.Normal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode152MaximumProductSubarrayTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(/* nums= */ new int[]{-4}, /* expected= */ -4),
        arguments( /* nums= */ new int[]{2, 3, -2, 4}, /* expected= */ 6)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void maxProduct(int[] nums, int expected) {
    Algorithm algorithm = new Normal();

    assertThat(algorithm.maxProduct(nums)).isEqualTo(expected);
  }
}