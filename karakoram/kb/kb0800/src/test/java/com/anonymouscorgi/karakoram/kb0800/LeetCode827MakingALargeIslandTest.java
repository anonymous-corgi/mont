package com.anonymouscorgi.karakoram.kb0800;

import static com.anonymouscorgi.karakoram.kb0800.LeetCode827MakingALargeIsland.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode827MakingALargeIslandTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* grid= */ new int[][]{{1, 0}, {0, 1}},
            /* expected= */ 3),
        arguments(
            /* grid= */ new int[][]{{1, 1}, {1, 0}},
            /* expected= */ 4),
        arguments(
            /* grid= */ new int[][]{{1, 1}, {1, 1}},
            /* expected= */ 4),
        arguments(
            /* grid= */ new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}},
            /* expected= */ 5)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void largestIsland(int[][] grid, int expected) {
    assertThat(Method.largestIsland(grid)).isEqualTo(expected);
  }
}