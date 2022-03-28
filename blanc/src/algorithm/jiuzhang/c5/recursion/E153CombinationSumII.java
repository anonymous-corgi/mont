package algorithm.jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E153CombinationSumII {
	
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if(num == null){
            return results;
        }
        
        Arrays.sort(num);
        helper(num, target, 0, new LinkedList<Integer>(), results);
        return results;
    }
    
    private void helper(int[] num,
                        int target,
                        int startIndex,
                        LinkedList<Integer> combination,
                        List<List<Integer>> results){
        if(target == 0){
            results.add(new ArrayList<Integer>(combination));
            return;
        }
        for(int i = startIndex; i < num.length; i++){
            if(num[i] > target){
                break;
            }
            combination.add(num[i]);
            helper(num, target - num[i], i + 1, combination, results);
            combination.removeLast();
        }
        return;
    }

}
