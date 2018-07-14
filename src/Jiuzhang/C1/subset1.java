package Jiuzhang.C1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subset1 {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		
		if (nums == null) {
			return results;
		}
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

		Arrays.sort(nums);

		List<Integer> subset = new ArrayList<>();
		subsetHelper(nums, 0, subset, results);

		return results;
	}

	private void subsetHelper(int[] nums, 
							  int startIndex, 
							  List<Integer> subset,
							  List<List<Integer>> results) {
		
		results.add(new ArrayList<Integer>(subset));
		for(int i = startIndex; i < nums.length; i++){
			subset.add(new Integer(nums[i]));
			subsetHelper(nums, i+1, subset, results);
			subset.remove(subset.size()-1);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		subset1 one = new subset1();
		int[] num = {1, 2, 3};
		for(int i = 0; i < one.subsets(num).size(); i++){
			System.out.print(one.subsets(num).get(i));
		}
	}
}
