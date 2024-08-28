package com.anonymouscorgi.karakoram.jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E15Permutations {
	
    @SuppressWarnings("null")
	public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null && nums.length == 0){
            return results;
        }
//        count = 0;
        Arrays.sort(nums);
        boolean[] checkUsed = new boolean[nums.length];
        for(int i = 0; i < checkUsed.length; i++){
            checkUsed[i] = false;
        }
        searcher(nums, checkUsed, new LinkedList<Integer>(), results);
        return results;
    }
    
//    public int total(){
//    	return count;
//    }
//
//    private int count;
//    
//    public int complexity(int n){
//    	int sum = 1;
//    	for(int i = 0; i < n - 1; i++){
//    		sum = sum * n;
//    	}
//    	return sum;
//    }
    
    private void searcher(int[] nums,
                        //   int startIndex,
                          boolean[] checkUsed,
                          LinkedList<Integer> partition,
                          List<List<Integer>> results){
        if(partition.size() == nums.length){
            results.add(new ArrayList<Integer>(partition));
            return;
        }
        for(int i = 0; i < nums.length; i++){
//        	count++;
            if(checkUsed[i]){
                continue;
            }
            partition.add(nums[i]);
            checkUsed[i] = true;
            searcher(nums, checkUsed, partition, results);
            partition.removeLast();
            checkUsed[i] = false;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E15Permutations one = new E15Permutations();
		int[] nums = {1,2,3,4,5,6,7};
		one.permute(nums);
//		int t = one.total();
//		int o = one.complexity(nums.length);
//		System.out.println(t);
//		System.out.println(o);
//		System.out.println(t > o);
	}

}
