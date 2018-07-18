package jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E135CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if(candidates == null){
            return results;
        }
        
        Arrays.sort(candidates);
        findCombination(candidates, target, 0, new LinkedList<Integer>(), results);
        return results;
    }
    
    private void findCombination(int[] candidates,
                                 int target,
                                 int startIndex,
                                 LinkedList<Integer> combination,
                                 List<List<Integer>> results){
        if(target == 0){
            results.add(new LinkedList<Integer>(combination));
        }
        for(int i = startIndex; i < candidates.length; i++){
            if(i != 0 && candidates[i] == candidates[i - 1]){
                continue;
            }
            if(candidates[i] > target){
                break;
            }
            combination.add(candidates[i]);
            findCombination(candidates, target - candidates[i], i, combination, results);
            combination.removeLast();
        }
    }
}
