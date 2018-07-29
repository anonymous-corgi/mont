package leetcode.p201to300;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import utils.Print;

public class LeetCode210CourseScheduleII {
  
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
    }
    int index = 0;
    int[] res = new int[numCourses];
    int[] ins = new int[numCourses];
    Map<Integer, Set<Integer>> pres = new HashMap<>();
    Queue<Integer> taskList = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      pres.put(i, new HashSet<Integer>());
    }
    if (prerequisites != null) {    
      for (int i = 0; i < prerequisites.length; i++) {
        ins[prerequisites[i][0]]++;
        pres.get(prerequisites[i][1]).add(prerequisites[i][0]);
      }
    }
    for (int i = 0; i < numCourses; i++) {
      if (ins[i] == 0) {
        taskList.offer(i);
      }
    }
    while (!taskList.isEmpty()) {
      int cursor = taskList.poll();
      res[index++] = cursor;
      for (Integer each : pres.get(cursor)) {
        if (--ins[each] == 0) {
          taskList.offer(each);
        }
      }
    }
    return index == numCourses ? res : new int[0];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode210CourseScheduleII one = 
        new LeetCode210CourseScheduleII();
    int numCourses = 2;
    int[][] prerequisites = {};
    Print.printArray(one.findOrder(numCourses, prerequisites));
  }

}
