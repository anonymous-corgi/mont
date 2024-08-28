package com.anonymouscorgi.karakoram.kb0200;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.anonymouscorgi.karakoram.kb0200.LeetCode200NumberOfIslands.Algorithm;
import com.anonymouscorgi.karakoram.kb0200.LeetCode200NumberOfIslands.UnionFind;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeetCode200NumberOfIslandsTest {

  private static Stream<Arguments> generateTestParametersStream()  {
    return Stream.of(
        arguments(/* grid= */
            new boolean[][]{{true, true, true, true, true, true},
                {true, false, false, false, false, true},
                {true, false, true, true, false, true},
                {true, false, false, false, false, true},
                {true, true, true, true, true, true}}, /* expected= */ 2),
        arguments(/* grid= */
            new boolean[][]{{true, true, true, true, true, true},
                {true, false, false, false, false, true},
                {true, false, true, true, true, true},
                {true, false, false, false, false, true},
                {true, true, true, true, true, true}}, /* expected= */ 1),
        arguments(/* grid= */
            new boolean[][]{{true, true, true, false, true, true},
                {false, false, true, false, false, true},
                {false, false, true, true, false, false},
                {true, false, false, true, false, false},
                {true, true, false, true, true, true}}, /* expected= */ 3));
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void numIslands(boolean[][] grip, int expected) {
    Algorithm algorithm = new UnionFind();

    assertThat(algorithm.numIslands(grip)).isEqualTo(expected);
  }
}