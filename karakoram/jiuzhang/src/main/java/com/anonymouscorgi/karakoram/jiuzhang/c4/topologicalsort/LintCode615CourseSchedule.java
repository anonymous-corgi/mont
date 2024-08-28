package com.anonymouscorgi.karakoram.jiuzhang.c4.topologicalsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 615 · Course Schedule
 * <p>
 * Description There are a total of n courses you have to take, labeled from 0 to n - 1. Before
 * taking some courses, you need to take other courses. For example, to learn course 0, you need to
 * learn course 1 first, which is expressed as [0,1]. Given the total number of courses and a list
 * of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, prerequisites = [[1,0]] Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2, prerequisites = [[1,0],[0,1]] Output: false
 */
interface LintCode615CourseSchedule {

  boolean canFinish(int numCourses, int[][] prerequisites);

  LintCode615CourseSchedule UniqueTopologicalOrdering_Method = new LintCode615CourseSchedule() {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
        return true;
      }

      // Array to keep track of the number of prerequisites (dependencies) for each course
      int[] dependencies = new int[numCourses];
      // List of sets to represent the graph, where each course points to the courses that depend on it
      List<Set<Integer>> dependents = new ArrayList<>();
      for (int i = 0; i < numCourses; i++) {
        dependents.add(new HashSet<>());
      }
      // Build the graph and fill the dependencies array
      for (int[] prerequisite : prerequisites) {
        int dependent = prerequisite[0];
        int dependency = prerequisite[1];
        if (dependents.get(dependency).add(dependent)) {
          dependencies[dependent]++;
        }
      }

      Queue<Integer> unblockedList = new ArrayDeque<>();
      Set<Integer> checkList = new HashSet<>();
      for (int i = 0; i < numCourses; i++) {
        if (dependencies[i] == 0) {
          unblockedList.offer(i);
          checkList.add(i);
        }
      }

      while (!unblockedList.isEmpty()) {
        Integer currentNode = unblockedList.poll();
        for (Integer neighbor : dependents.get(currentNode)) {
          if (--dependencies[neighbor] == 0) {
            unblockedList.offer(neighbor);
            checkList.add(neighbor);
          }
        }
      }
      return checkList.size() == numCourses;
    }
  };
}
