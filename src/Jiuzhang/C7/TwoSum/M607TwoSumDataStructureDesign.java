package Jiuzhang.C7.TwoSum;

import java.util.HashMap;
import java.util.Map;

public class M607TwoSumDataStructureDesign {
	
    public M607TwoSumDataStructureDesign() {
        item = new HashMap<Integer, Integer>();
    } 
    
    public void add(int number) {
        // write your code here
        if (item.containsKey(number)) {
            item.put(number, item.get(number) + 1);
        } else {
            item.put(number, 1);
        }
    }

    /*
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        for (Integer each : item.keySet()) {
            if ((value == (2 * each)) && (item.get(each) > 1)) {
                return true;
            } else if (item.containsKey(value - each)) {
                return true;
            }
        }
        return false;
    }
    
    private Map<Integer, Integer> item;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M607TwoSumDataStructureDesign one = new M607TwoSumDataStructureDesign();
		one.add(2);
		one.add(3);
		one.find(4);
		one.find(5);
		one.find(6);
		one.add(3);
		one.find(6);

	}

}
