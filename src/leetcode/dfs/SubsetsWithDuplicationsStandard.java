package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.Print;

public class SubsetsWithDuplicationsStandard {

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}
	
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubsetsWithDuplicationsStandard one = new SubsetsWithDuplicationsStandard();
		int[] nums = {1, 2, 2, 3};
		List<List<Integer>> list = one.subsetsWithDup(nums);
		System.out.println(list.size());
		for (int i = 0, len1 = list.size(); i < len1; i++) {
			Print.printList(list.get(i), 0, list.get(i).size());
		}
	}

}
