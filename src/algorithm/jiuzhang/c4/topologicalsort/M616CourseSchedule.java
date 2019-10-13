package algorithm.jiuzhang.c4.topologicalsort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class M616CourseSchedule {
	
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int[] empty = {};
        int point = 0;
        if(numCourses == 0){
            return empty;
        }
        
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        
        for(int i = 0; i < numCourses; i++){
            neighbors.put(i, new LinkedList<Integer>());
            inDegree.put(i, 0);
        }
        for(int i = 0; i < prerequisites.length; i++){
            int v = prerequisites[i][0];
            int u = prerequisites[i][1];
            if(neighbors.get(u).add(v)){
                inDegree.put(v, inDegree.get(v) + 1);
            }
        }
        
        Queue<Integer> taskList = new LinkedList<>();
        // Set<Integer> checkList = new HashSet<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree.get(i) == 0){
                taskList.offer(i);
                result[point++] = i;
                // checkList.add(i);
            }
        }
        while(!taskList.isEmpty()){
            Integer node = taskList.poll();
            for(Integer neighbor : neighbors.get(node)){
                if(inDegree.get(neighbor) == 1){
                    taskList.offer(neighbor);
                    result[point++] = neighbor;
                    // checkList.add(neighbor);
                }
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
            }
        }
        
        if(point == numCourses){
            return result;
        }else{
            return empty;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M616CourseSchedule one = new M616CourseSchedule();
		int[][] prerequisites = {{0,1},{1,2}};
		System.out.println(one.findOrder(3, prerequisites));
	}

}
