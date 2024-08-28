package com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort;

import static com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort.LintCode615CourseSchedule.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LintCode615CourseScheduleTest {

  private static Stream<Arguments> generateTestParametersStream() {
    return Stream.of(
        arguments(
            /* numCourses= */ 10,
            /* prerequisites= */
            new int[][]{{5, 8}, {3, 5}, {1, 9}, {4, 5}, {0, 2}, {1, 9}, {7, 8}, {4, 9}},
            /* expected= */ true)
    );
  }

  @ParameterizedTest
  @MethodSource("generateTestParametersStream")
  void canFinish(int numCourses, int[][] prerequisites, boolean expected) {
    assertThat(UniqueTopologicalOrdering_Method.canFinish(numCourses, prerequisites)).isEqualTo(
        expected);
  }
}