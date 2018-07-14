package Jiuzhang.C7.TwoSum;

import java.util.HashMap;
import java.util.Map;

public class M610DifferenceEqualsToTarget {
	
    public int[] twoSum7(int[] nums, int target) {
        int[] result = {0, 0};
        if(nums == null || nums.length == 0){
            return result;
        }
        Map<Integer, Integer> record = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            record.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++){
            if(record.containsKey((nums[i] + target))){
                result[0] = record.get(nums[i] + target) + 1;
                result[1] = i + 1;
                break;
            }else if (record.containsKey(nums[i] - target)){
                result[0] = record.get(nums[i] - target) + 1;
                result[1] = i + 1;
                break;
            }
        }
        if(result[0] > result[1]){
            result[1] += result[0];
            result[0] = result[1] - result[0];
            result[1] -= result[0];
        }
        return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M610DifferenceEqualsToTarget one = new M610DifferenceEqualsToTarget();
		int[] arr = {1, 0, -1};
		System.out.println(one.twoSum7(arr, -2));
 
	}

}
