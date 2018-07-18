package interview.google.oa;

import java.util.HashSet;
import java.util.Set;

import utils.test.Test;

public class NextTimePermutation {
	
	private String result;
	private int oriMins;
	private int minDiff = Integer.MAX_VALUE;
	
	public String nextClosestTime(String time) {
		if (time == null || time.length() != 5) {
			return time;
		}
		HashSet<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder(time);
		sb.delete(2, 3);
		for (int i = 0; i < 4; i++) {
			set.add(Integer.parseInt(sb.substring(i, i + 1)));
		}
		result = time;
		if (set.size() == 1) {
			return result;
		}
		oriMins = Integer.parseInt(sb.substring(0, 2)) * 60 + Integer.parseInt(sb.substring(2, 4));
//		StringBuilder sBuilder = new
		dfs(set, 0, new int[4]);
		return result;
	}
	
	private void dfs(Set<Integer> set, int position, int[] t) {
		if (position == 4) {
			int mins = countMin(t);
			int diff = mins >= oriMins ? mins - oriMins : 1440 - oriMins + mins;
			if (diff != 0 && diff < minDiff) {
				minDiff = diff;
				result = "" + t[0] + t[1] + ":" +t[2] + t[3];
			}
			return;
		}
		
		for (Integer each : set) {
			if (position == 0 && each > 2) {
				continue;
			}
			if (position == 1 && (t[0] * 10 + each) > 23) {
				continue;
			}
			if (position == 2 && each > 5) {
				continue;
			}
			t[position] = each;
			dfs(set, position + 1, t);
		}
	}
	
	private int countMin(int[] t) {
		return (t[0] * 10 + t[1]) * 60 + t[2] * 10 + t[3];
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tests = {"15:59", "10:11", "01:21"};
		String[] expecteds = {"19:11", "11:00", "01:22"};
				for (int i = 0, len = tests.length; i < len; i++) {
			Test.assertEquals(expecteds[i], new NextTimePermutation().nextClosestTime(tests[i]));
		}


	}

}
