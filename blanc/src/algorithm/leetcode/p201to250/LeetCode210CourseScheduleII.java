package algorithm.leetcode.p201to250;

import java.util.*;

@SuppressWarnings("unused")
public class LeetCode210CourseScheduleII {

    private interface Method {
        int[] findOrder(int numCourses, int[][] prerequisites);
    }

    private static class BFS implements Method {

        @SuppressWarnings("unchecked")
        @Override
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int index = 0;
            int[] res = new int[numCourses];
            int[] inDegrees = new int[numCourses];
            List<Integer>[] successorsArray = new List[numCourses];
            Queue<Integer> taskQueue = new ArrayDeque<>();

            for (int i = 0; i < numCourses; i++) {
                successorsArray[i] = new ArrayList<>(0);
            }
            for (int[] prerequisite : prerequisites) {
                inDegrees[prerequisite[0]]++;
                successorsArray[prerequisite[1]].add(prerequisite[0]);
            }
            for (int i = 0; i < numCourses; i++) {
                if (inDegrees[i] == 0) {
                    taskQueue.offer(i);
                }
            }

            while (!taskQueue.isEmpty()) {
                int course = taskQueue.poll();
                res[index++] = course;
                for (int successor : successorsArray[course]) {
                    if (--inDegrees[successor] == 0) {
                        taskQueue.offer(successor);
                    }
                }
            }

            return index == numCourses ? res : new int[0];
        }
    }
}
