package Jiuzhang.C4.TopologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class M605SequenceReconstruction {
	
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
        if(org == null) {
            return false;
        }
        if(seqs == null || seqs.length == 0 || seqs[0].length == 0) {
            return false;
        }
        
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Set<Integer>> successors = new HashMap<>();
        
        for(int each : org) {
            indegree.put(each, 0);
            successors.put(each, new HashSet<Integer>());
        }
        
        for(int i = 0; i < seqs.length; i++) {
            if(!successors.containsKey(seqs[i][0])) {
                return false;
            }
            for(int j = 1; j < seqs[0].length; j++) {
                int p = seqs[i][j - 1];
                int s = seqs[i][j];
                if(!successors.containsKey(s)) {
                    return false;
                }
                if(successors.get(p).add(s)) {
                    indegree.put(s, indegree.get(s) + 1);
                }
            }
        }
        // if(indegree.size() != org.length) {
        //     return false;
        // }
        
        Queue<Integer> taskList = new LinkedList<>();
        
        for(Integer each : indegree.keySet()) {
            if(indegree.get(each) == 0){
                taskList.offer(each);
            }
        }
        
        int index = 0;
        while(taskList.size() == 1) {
            Integer current = taskList.poll();
            if(current != org[index++]) {
                return false;
            }
            for(Integer successor : successors.get(current)) {
                if(indegree.get(successor) == 1) {
                    taskList.offer(successor);
                }
                indegree.put(successor, indegree.get(successor) - 1);
            }
        }
        return (index == org.length);
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] org = {1,2,3};
		int[][] seqs ={{1,2}, {1,3},{2,3}};
		M605SequenceReconstruction one = new M605SequenceReconstruction();
		one.sequenceReconstruction(org, seqs);

	}

}
