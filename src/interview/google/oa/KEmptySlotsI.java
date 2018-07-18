package interview.google.oa;

import java.util.TreeSet;

public class KEmptySlotsI {
     
    public int kEmptySlots(int[] flowers, int k) {
        // Write your code here
        if(flowers == null) {
            return -1;
        }
        int len = flowers.length;
        if (len == 0) {
        	return -1;
        }
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < len; i++) {
        	set.add(flowers[i]);
        	Integer hi = set.higher(flowers[i]);
        	if (hi != null && hi - flowers[i] == k + 1) {
        		return i + 1;
        	}
        	Integer lo = set.lower(flowers[i]);
        	if (lo != null && flowers[i] - lo == k + 1) {
        		return i + 1;
        	}
        }
        return -1;
    }
    	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KEmptySlotsI one = new KEmptySlotsI();
//		int[] flowers = {23,36,49,20,9,75,11,96,38,91,78,43,58,98,47,32,18,46,69,71,66,16,87,10,82,86,59,34,73,15,79,8,90,42,19,45,27,37,6,31,53,22,100,85,26,54,70,63,80,81,7,5,52,68,3,17,74,1,94,99,35,83,93,62,55,64,56,21,84,40,41,33,89,51,72,60,88,48,39,4,12,65,44,29,24,13,28,77,76,25,97,57,30,2,92,14,61,50,95,67};
//		int[] flowers = {1,3,6,4,2,5};
		int[] flowers = {6,5,8,9,7,1,10,2,3,4};
		int k = 2;
		System.out.println(one.kEmptySlots(flowers, k));

	}

}
