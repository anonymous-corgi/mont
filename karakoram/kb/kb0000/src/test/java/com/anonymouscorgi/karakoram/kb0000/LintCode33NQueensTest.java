package com.anonymouscorgi.karakoram.kb0000;

import static com.anonymouscorgi.karakoram.kb0000.LintCode33NQueens.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode33NQueensTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* n= */ 1,
            /* expected= */ List.of(List.of("Q"))),
        arguments(
            /* n= */ 2,
            /* expected= */ List.of()),
        arguments(
            /* n= */ 3,
            /* expected= */ List.of()),
        arguments(
            /* n= */ 4,
            /* expected= */
            List.of(
                List.of("..Q.", "Q...", "...Q", ".Q.."),
                List.of(".Q..", "...Q", "Q...", "..Q."))),
        arguments(
            /* n= */ 5,
            /* expected= */
            List.of(
                List.of("Q....", "..Q..", "....Q", ".Q...", "...Q."),
                List.of("Q....", "...Q.", ".Q...", "....Q", "..Q.."),
                List.of(".Q...", "...Q.", "Q....", "..Q..", "....Q"),
                List.of(".Q...", "....Q", "..Q..", "Q....", "...Q."),
                List.of("..Q..", "Q....", "...Q.", ".Q...", "....Q"),
                List.of("..Q..", "....Q", ".Q...", "...Q.", "Q...."),
                List.of("...Q.", "Q....", "..Q..", "....Q", ".Q..."),
                List.of("...Q.", ".Q...", "....Q", "..Q..", "Q...."),
                List.of("....Q", ".Q...", "...Q.", "Q....", "..Q.."),
                List.of("....Q", "..Q..", "Q....", "...Q.", ".Q...")))
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void solveNQueens(int n, List<List<String>> expected) {
    assertThat(DFS.solveNQueens(n)).containsExactlyElementsIn(expected);
  }
}