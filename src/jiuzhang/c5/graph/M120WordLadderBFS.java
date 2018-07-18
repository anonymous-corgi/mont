package jiuzhang.c5.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class M120WordLadderBFS {
	
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        int result = 0;
        
        dict.add(end);
        Queue<String> taskList = new LinkedList<>();
        Queue<String> elementBin = new LinkedList<>(dict);
        
        if(start == null || end == null){
            return 0;
        }
        if(start.equals(end)){
            return 1;
        }
        
        taskList.offer(start);
        
        int size = 1;
        while(!taskList.isEmpty()){
            
            size++;
            int number = taskList.size();
            for(int i = 0; i < number; i++){
                String head = taskList.poll();
                if(head == null){
                	break;
                }
                Queue<String> recycleBin = new LinkedList<>();
                while(!elementBin.isEmpty()){
                	String next = elementBin.poll();
               		if(nextWord(head, next)){
                        if(next.equals(end)){
                            taskList.clear();
                            result = size;
                            break;
                        }
                        taskList.add(next);
                    }else {
                    	recycleBin.add(next);
					}
                }
                elementBin = recycleBin;
            }
        }
        
        if(result != 0){
            return result;
        }
        return 0;
    }
    
    

    
    private boolean nextWord(String start, String next){
        int num = 0;
        for(int i = 0; i < start.length(); i++){
            if(start.charAt(i) != next.charAt(i)){
                num++;
            }
        }
        if(num == 1){
            return true;
        }else{
            return false;
        }
    }

	public static void main(String[] args) {
		M120WordLadderBFS one = new M120WordLadderBFS();
		Set<String> dict = new HashSet<>();
		dict.add("b");
//		dict.add("dot");
//		dict.add("dog");
//		dict.add("lot");
//		dict.add("log");
		
		System.out.println(one.ladderLength("a","a", dict));

	}

}
