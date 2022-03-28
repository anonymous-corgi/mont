package algorithm.jiuzhang.c8.heappriorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M171Anagrams {
	
    public List<String> anagrams(String[] strs) {
        // write your code here
        List<String> result = new ArrayList<>();
        if(strs == null || strs.length == 0){
            return result;
        }
        Map<Integer, List<String>> stub = new HashMap<>();
        
        for(String str : strs){
            int code = getcode(str);
            if(!stub.containsKey(code)){
                stub.put(code, new ArrayList<String>());
            }
            stub.get(code).add(str);
        }
        for(Map.Entry<Integer, List<String>> entry : stub.entrySet()){
            if(entry.getValue().size() > 1){
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
    
    private int getcode(String str){
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        int code = 1;
        for(int i = 0; i < str.length(); i++){
            code *= prime[str.charAt(i) - 'a'];
        }
        code = code % 100000000;
        return code;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
