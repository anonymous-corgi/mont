package com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort;

import static com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort.LintCode616CourseScheduleII.UniqueTopologicalOrdering_Method;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode616CourseScheduleIITest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* numCourses= */ 2,
            /* prerequisites= */ new int[][]{{1, 0}},
            /* expected= */ List.of(List.of(0, 1))),
        arguments(
            /* numCourses= */ 4,
            /* prerequisites= */ new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}},
            /* expected= */ List.of(List.of(0, 1, 2, 3), List.of(0, 1, 2, 3))),
        arguments(
            /* numCourses= */ 3,
            /* prerequisites= */ new int[][]{{0, 1}, {1, 2}},
            /* expected= */ List.of(List.of(2, 1, 0)))
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void findOrder(int numCourses, int[][] prerequisites, List<List<Integer>> expected) {
    int[] result = UniqueTopologicalOrdering_Method.findOrder(numCourses, prerequisites);
    List<Integer> resultList = Arrays.stream(result).boxed().collect(Collectors.toList());

    assertThat(resultList).isIn(expected);
  }
}