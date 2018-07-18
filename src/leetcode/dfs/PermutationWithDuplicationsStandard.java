package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.test.Print;

public class PermutationWithDuplicationsStandard {
	
	public List<List<Integer>> permuteUnique(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
	    return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
	    if(tempList.size() == nums.length){
	        list.add(new ArrayList<>(tempList));
	    } else{
	        for(int i = 0; i < nums.length; i++){
	            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
	            used[i] = true; 
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, used);
	            used[i] = false; 
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationWithDuplicationsStandard one = new PermutationWithDuplicationsStandard();
		int[] nums = {1, 2, 2, 3};
		List<List<Integer>> list = one.permuteUnique(nums);
		System.out.println(list.size());
		for (int i = 0, len1 = list.size(); i < len1; i++) {
			Print.printList(list.get(i), 0, list.get(i).size());
		}
	}
	
}