package com.anonymouscorgi.karakoram.kb0150;

import static com.anonymouscorgi.karakoram.kb0150.LeetCode152MaximumProductSubarray.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
    assertThat(Method.maxProduct(nums)).isEqualTo(expected);
  }
}