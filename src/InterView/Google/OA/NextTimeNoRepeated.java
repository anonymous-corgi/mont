package InterView.Google.OA;

import java.util.TreeMap;

public class NextTimeNoRepeated {
	
	private final int[] highestNum = {3, 4, 6, 10};
	
	public String nextClosestTime(String time) {
		if (time == null || time.length() != 5) {
			return time;
		}
		StringBuilder sb = new StringBuilder(time);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int[] timeArr = new int[4];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			if (i == 2) {
				continue;
			}
			timeArr[index] = Integer.parseInt(time.substring(i, i + 1));
			if (map.containsKey(timeArr[index])) {
				map.put(timeArr[index], map.get(timeArr[index]) + 1);
			} else {
				map.put(timeArr[index], 1);
			}
 			index++;
		}
		
		if (map.size() == 1) {
			return time;
		}
		int lowestNum = map.firstKey();
		index = 3; 
		Integer cursor = map.higherKey(timeArr[4]);
		for (int i = 4; i >= 0; i--) {
			if (i == 2) {
				continue;
			}
//			cursor = 
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
