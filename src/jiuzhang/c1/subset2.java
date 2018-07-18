package jiuzhang.c1;

import java.util.ArrayList;
import java.util.Arrays;

public class subset2 {
	
	public ArrayList<ArrayList<Integer>> subsetsWithout(int[] nums){
		ArrayList<ArrayList<Integer>> results = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return results;
		}
		
		Arrays.sort(nums);
		ArrayList<Integer> subset = new ArrayList<>();
		subsetHelper(nums, 0, subset, results);
		return results;
	}
	private void subsetHelper(int[] nums,
							  int index,
							  ArrayList<Integer> subset,
							  ArrayList<ArrayList<Integer>> results){
		if(index > nums.length-1){
			results.add(new ArrayList<>(subset));
		}else {
			subsetHelper(nums, index+1, subset, results);
			subset.add(nums[index]);
			subsetHelper(nums, index+1, subset, results);
			subset.remove(subset.size() - 1);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		subset2 one = new subset2();
		int[] num = {1, 2, 3};
		for(int i = 0; i < one.subsetsWithout(num).size(); i++){
			System.out.print(one.subsetsWithout(num).get(i));
		}
		
	}

}
