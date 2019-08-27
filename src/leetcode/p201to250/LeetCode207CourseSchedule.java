package leetcode.p201to250;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unused")
public class LeetCode207CourseSchedule {

    private interface Method {
        boolean canFinish(int numCourses, int[][] prerequisites);
    }

    private static class BFS implements Method {

        @SuppressWarnings("unchecked")
        @Override
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int finished = 0;
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
                finished++;
                int course = taskQueue.poll();
                for (int successor : successorsArray[course]) {
                    if (--inDegrees[successor] == 0) {
                        taskQueue.offer(successor);
                    }
                }
            }

            return finished == numCourses;
        }
    }
}
