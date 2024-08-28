package com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 616 · Course Schedule II
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have
 * prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1] Given the total number of courses and a list of prerequisite pairs, return the
 * ordering of courses you should take to finish all courses. There may be multiple correct orders,
 * you just need to return one of them. If it is impossible to finish all courses, return an empty
 * array.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, prerequisites = [[1,0]] Output: [0,1]
 * <p>
 * Example 2:
 * <p>
 * Input: n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] Output: [0,1,2,3] or [0,2,1,3]
 */
interface LintCode616CourseScheduleII {

  int[] findOrder(int numCourses, int[][] prerequisites);

  LintCode616CourseScheduleII UniqueTopologicalOrdering_Method = new LintCode616CourseScheduleII() {

    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      if (numCourses == 0) {
        return new int[0];
      }

      int[] dependencies = new int[numCourses];
      Map<Integer, Set<Integer>> dependents = new HashMap<>();
      for (int i = 0; i < numCourses; i++) {
        dependents.put(i, new HashSet<>());
      }

      for (int[] prerequisite : prerequisites) {
        int v = prerequisite[0];
        int u = prerequisite[1];
        if (dependents.get(u).add(v)) {
          dependencies[v]++;
        }
      }

      int resultCount = 0;
      int[] result = new int[numCourses];
      Queue<Integer> unblockedList = new LinkedList<>();
      for (int i = 0; i < numCourses; i++) {
        if (dependencies[i] == 0) {
          unblockedList.offer(i);
          result[resultCount++] = i;
        }
      }

      while (!unblockedList.isEmpty()) {
        Integer unblockedNode = unblockedList.poll();
        for (Integer dependent : dependents.get(unblockedNode)) {
          if (--dependencies[dependent] == 0) {
            unblockedList.offer(dependent);
            result[resultCount++] = dependent;
          }
        }
      }

      return resultCount == numCourses ? result : new int[0];
    }
  };
}
