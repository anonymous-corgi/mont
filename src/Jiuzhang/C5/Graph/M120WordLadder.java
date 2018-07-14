package Jiuzhang.C5.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M120WordLadder {
	
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        result = Integer.MAX_VALUE;
        if(start == null || end == null){
            return 0;
        }
        dict.add(end);
        Map<String, Boolean> checkList = new HashMap<>();
        for(String each : dict){
            checkList.put(each, false);
        }
        searcher(start, end, 2, checkList, dict);
        if(result != Integer.MAX_VALUE){
            return result;
        }
        return 0;
    }
    private int result;
    
    private void searcher(String start,
                          String end,
                          int times,
                          Map<String, Boolean> checkList,
                          Set<String> options){
        if(times > result){
            return;
        }
        for(String next : options){
        	if(!checkList.get(next)){
        		if(nextWord(start, next)){
        			if(next.equals(end)){
        				if(times < result){
        					result = times;
        				}
        				break;
        			}
        			checkList.put(next, true);
        			searcher(next, end, times + 1, checkList, options);
        			checkList.put(next, false);
        		}
        	}
        }
                              
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
		M120WordLadder one = new M120WordLadder();
		Set<String> dict = new HashSet<>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		System.out.println(one.ladderLength("hit","cog", dict));
	}
}
