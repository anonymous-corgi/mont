package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import utils.Print;

public class Permutation {
	
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) {
			return results;
		}
		int len = nums.length;
		if (len == 0) {
			return results;
		}
		Arrays.sort(nums);
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			queue.offer(nums[i]);
		}
		dfs(queue,  new ArrayList<Integer>(), results);
		
		return  results;
	}
	
	private void dfs(Queue<Integer> res, List<Integer> aResult, List<List<Integer>> results) {
		if (res.isEmpty()) {
			results.add(new ArrayList<Integer>(aResult));
			return;
		}
		for (int i = 0, len = res.size(); i < len; i++) {
			Integer cursor = res.poll();
			aResult.add(cursor);
			dfs(res, aResult, results);
			res.offer(cursor);
			aResult.remove(aResult.size() - 1);
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Permutation one = new Permutation();
		int[] nums = {1, 3, 2, 4};
		List<List<Integer>> list = one.permute(nums);
		System.out.println(list.size());
		for (int i = 0, len1 = list.size(); i < len1; i++) {
			Print.printList(list.get(i), 0, list.get(i).size());
		}
	}

}
