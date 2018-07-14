package InterView.Google.OA;

import java.util.TreeSet;

import utils.test.Test;

public class LastTime {
	
	private int[] highestNum = {3, 4, 6, 10};
	
	public String lastClosestTime(String time) {
		if (time == null || time.length() != 5) {
			return time;
		}
		StringBuilder sb= new StringBuilder(time);
		TreeSet<Integer> set = new TreeSet<>();
		int[] timeArr = new int[4];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			if (i == 2) {
				continue;
			}
			timeArr[index] = Integer.parseInt(time.substring(i, i + 1));
			set.add(timeArr[index]);
			index++;
		}
		
		if (set.size() == 1) {
			return time;
		}
		
		Integer cursor;
		index = 3;
		for (int i = 4; i >= 0; i--) {
			if (i == 2) {
				continue;
			}
			cursor = set.lower(timeArr[index]);
			if (cursor != null) {
				sb.replace(i, i + 1, "" + cursor);
				return sb.toString();
			} else {
				Integer largest = set.lower(highestNum[index]);
				sb.replace(i, i + 1, "" + largest);
			}
			index--;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LastTime one = new LastTime();
		String[] tests = {"23:59", "22:55", "22:11"};
		String[] expecteds = {"23:55", "22:52", "21:22"};
		for (int i = 0, len = tests.length; i < len; i++) {
			Test.assertEquals(expecteds[i], one.lastClosestTime(tests[i]));
		}
	}
	
}
