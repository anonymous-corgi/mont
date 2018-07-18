package Jiuzhang.DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LintCode622FrogJump {
	
    public static boolean canCross(int[] stones) {
        // write your code here
        if (stones[0] != 0 || stones[1] - stones[0] != 1) {
            return false;
        }
        int len = stones.length;
        HashMap<Integer, Set<Integer>> records = new HashMap<>(len * 2);
        for (int i = 1; i < len; i++) {
            records.put(stones[i], new HashSet<Integer>());
        }
        records.get(stones[1]).add(1);
        for (int i = 1; i < len - 1; i++) {
            Set<Integer> record = records.get(stones[i]);
            for (int prevStep : record) {
                for (int j = -1; j < 2; j++) {
                    int nextStep = prevStep + j;                    
                    if (nextStep < 1) {
                    	continue;
                    }
                    int nextPosition = stones[i] + nextStep;
                    if (records.containsKey(nextPosition)) {
                        records.get(nextPosition).add(nextStep);
                    }
                }
            }
        }        
        return records.get(stones[len - 1]).size() > 0;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0,1,3,5,6,8,12,17};
		System.out.println("Can Cross: " + canCross(arr));
	}

}
