package InterView.Google.OA;

import utils.test.Test;

public class NextTimeNoRepeated {
	
	private int minDiff = Integer.MAX_VALUE;
	private int oriMins;
	private String result;
	
	public String nextClosestTime(String time) {
		if (time == null || time.length() != 5) {
			return time;
		}
		StringBuilder sb = new StringBuilder(time);
		int[] res = new int[4];
		sb.delete(2, 3);
		for (int i = 0; i < 4; i++) {
			res[i] = Integer.parseInt(sb.substring(i, i + 1));
		}
		oriMins = countMin(res);
		result = time;
		dfs(res, new int[4], 0, new boolean[4]);
		return result;
	}
	
	private void dfs(int[] res, int[] t, int position, boolean[] isUsed) {
		if (position == 4) {
			int mins = countMin(t);
			int diff = mins >= oriMins ? mins - oriMins : 1440 - oriMins + mins;
			if (diff != 0 && diff < minDiff) {
				minDiff = diff;
				result = "" + t[0] + t[1] + ":" + t[2] + t[3];
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (isUsed[i]) {
				continue;
			}
			if (position == 0 && res[i] > 2) {
				continue;
			}
			if (position == 1 && (t[0] * 10 + res[i]) > 23) {
				continue;
			}
			if (position == 2 && res[i] > 5) {
				continue;
			}
			isUsed[i] = true;
			t[position] = res[i];
			dfs(res, t, position + 1, isUsed);
			isUsed[i] = false;
		}
	}
	
	private int countMin(int[] time) {
		return (time[0] * 10 + time[1]) * 60 + time[2] * 10 + time[3];
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tests = {"15:59", "10:11", "01:21"};
		String[] expecteds = {"19:55", "11:01", "02:11"};
//		String[] tests = {"01:21"};
//		String[] expecteds = {"02:11"};
		for (int i = 0, len = tests.length; i < len; i++) {
			Test.assertEquals(expecteds[i], new NextTimeNoRepeated().nextClosestTime(tests[i]));
		}
	}

}
