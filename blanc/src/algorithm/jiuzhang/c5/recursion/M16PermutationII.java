package algorithm.jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class M16PermutationII {
	
    public List<List<Integer>> permuteUnique(int[] nums) {
        results = new ArrayList<>();
        if(nums == null){
            return results;
        }
        if(nums.length == 0){
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        isUsed = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            isUsed[i] = false;
        }
        
        searcher(nums, new LinkedList<Integer>()); 
        return results;
    }
    
    private List<List<Integer>> results;
    private boolean[] isUsed;
    
    private void searcher(int[] nums,
                          LinkedList<Integer> partition){
        if(partition.size() == nums.length){
            results.add(new ArrayList<Integer>(partition));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(isUsed[i]){
                continue;
            }
            if(i != 0 && nums[i] == nums[i - 1] && !isUsed[i - 1]){
                continue;
            }
            partition.add(nums[i]);
            isUsed[i] = true;
            searcher(nums, partition);
            partition.removeLast();
            isUsed[i] = false;
        }
    }

}
