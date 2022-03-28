package algorithm.jiuzhang.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LintCode603LargestDivisibleSubset {
	
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Integer i : nums) {
            if (!map.containsKey(i)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(i, list);
            }
        }
        int[] f = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (f[j] + 1 > f[i]) {
                        map.get(nums[i]).clear();
                        map.get(nums[i]).addAll(map.get(nums[j]));
                        map.get(nums[i]).add(nums[i]);
                        f[i] = f[j] + 1;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int t = 0; t < f.length; t++) {
            if (f[t] > max) {
                max = f[t];
                maxIndex = t;
            }
        }
        return map.get(nums[maxIndex]);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
