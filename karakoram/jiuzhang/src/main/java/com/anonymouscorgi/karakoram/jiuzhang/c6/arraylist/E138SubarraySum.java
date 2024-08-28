package com.anonymouscorgi.karakoram.jiuzhang.c6.arraylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E138SubarraySum {
	
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        
        Map<Integer, Integer> sumRecord = new HashMap<>();
        sumRecord.put(0, -1);
        
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sumRecord.containsKey(sum)){
                result.add(sumRecord.get(sum) + 1);
                result.add(i);
                break;
            }
            sumRecord.put(sum, i);
        }
        return result;
    }

}
