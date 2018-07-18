package jiuzhang.c4.topologicalsort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class M615CourseSchedule {
	
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        if(numCourses == 0){
            return true;
        }
        if(prerequisites == null || prerequisites.length == 0){
            return true;
        }
        
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        Map<Integer, Integer> inDegree= new HashMap<>();
        for(int i = 0; i < numCourses; i++){
            neighbors.put(i, new HashSet<Integer>());
            inDegree.put(i, 0);
        }
        for(int i = 0; i < prerequisites.length; i++){
            int v = prerequisites[i][0];
            int u = prerequisites[i][1];
            if(neighbors.get(u).add(v)) {
	            	inDegree.put(v, inDegree.get(v) + 1);
            }
        }
        
        Queue<Integer> taskList = new LinkedList<>();
        Set<Integer> checkList = new HashSet<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree.get(i) == 0){
                taskList.offer(i);
                checkList.add(i);
            }
        }
        
        while(!taskList.isEmpty()){
            Integer currentNode = taskList.poll();
            for(Integer neighbor : neighbors.get(currentNode)){
                if(inDegree.get(neighbor) == 1){
                    taskList.offer(neighbor);
                    checkList.add(neighbor);
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                }else{
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                }
            }
        }
        if(checkList.size() == numCourses){
            return true;
        }else{
            return false;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M615CourseSchedule one = new M615CourseSchedule();
		int[][] prerequisites = {{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}};
		System.out.println(one.canFinish(10, prerequisites));
	}
}
