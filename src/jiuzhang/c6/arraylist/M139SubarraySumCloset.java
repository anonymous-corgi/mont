package jiuzhang.c6.arraylist;

import java.util.Arrays;
import java.util.Comparator;

public class M139SubarraySumCloset {
	
    public int[] subarraySumClosest(int[] nums) {
    	int[] result = new int[2];
    	if(nums == null || nums.length ==0){
    		return result;
    	}
    	Record one = new Record();
        Record[] unit= new Record[nums.length + 1];
        unit[0] = new Record(0, 0);
        
        for(int i = 0; i < nums.length + 1; i++){
        	int sum = nums[i] + unit[i - 1].sum;
            unit[i] = new Record(i, sum);
        }
        
        Arrays.sort(unit, one);
        
        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
        	if(one.compare(unit[i + 1], unit[i]) < minSum){
        		minSum = one.compare(unit[i + 1], unit[i]);
        		int[] temp = new int[]{unit[i].index - 1, unit[i - 1].index - 1};
                Arrays.sort(temp);
        		result[0] = temp[0] + 1;
        		result[1] = temp[1];
        	}
        }
        return result;
    }
    
    private class Record implements Comparator<Record>{
        public int index;
        public int sum;
        
        public Record(){
        }
        public Record(int i, int s){
            index = i;
            sum = s;
        }
        
        public int compare(Record a, Record b){
            return a.sum - b.sum;
        }
    }

}
