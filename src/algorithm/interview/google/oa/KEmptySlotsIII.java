package algorithm.interview.google.oa;

import java.util.TreeSet;

public class KEmptySlotsIII {
	
	private TreeSet<Integer> set = new TreeSet<Integer>();

    public int kEmptySlotsII(int[][] flowers, int k) {
    	if (flowers == null) {
    		return -1;
    	}
    	int m = flowers.length;
    	if (m == 0) {
    		return -1;
    	}
    	if (flowers[0] == null) {
    		return -1;
    	}
    	int len = flowers[0].length;
    	if (len == 0) {
    		return -1;
    	}
    	int result = -1;
    	for (int i = 0; i < m; i++) {
    		int re = findKEmpty(flowers[i], k);
    		result = Math.max(re, result);
    	}
    	return result;    			
    } 
    
    private int findKEmpty(int[] nums, int k) {
    	int result = -1;
    	set.clear();
    	for (int i = 0, len = nums.length; i < len; i++) {
    		set.add(nums[i]);
    		Integer lo = set.lower(nums[i]);
    		if (lo != null && nums[i] - lo == k + 1) {
    			result = i + 1 > result ? i + 1 : result;
    		}
    		Integer hi = set.higher(nums[i]);
    		if (hi != null && hi - nums[i] == k + 1) {
    			result = i + 1 > result ? i + 1 : result;
    		}
    	}
    	return result;
    }   	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
