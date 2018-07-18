package jiuzhang.c4.levelordertraversal;

import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class M178GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if(n == 0){
            return false;
        }
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        Queue<Integer> taskList = new LinkedList<>();
        Set<Integer> checkList = new HashSet<>();
        
        taskList.offer(0);
        checkList.add(0);
        while(!taskList.isEmpty()){
            int node = taskList.poll();
            for(Integer eachNeighbor : graph.get(node)){
                if(checkList.contains(eachNeighbor)){
                    continue;
                }
                checkList.add(eachNeighbor);
                taskList.offer(eachNeighbor);
            }
        }
        return (checkList.size() == n);

    }
    
    private Map<Integer, Set<Integer>> initializeGraph(int num, int[][] edges){
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < num; i++){
            graph.put(i, new HashSet<Integer>());
        }
        for(int j = 0; j < edges.length; j++){
            int u = edges[j][0];
            int v = edges[j][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
