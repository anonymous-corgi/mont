package InterView.Google.OA;

import java.util.TreeSet;

import utils.test.Test;

public class NextTime {
	
    public String nextClosestTime(String time) {
        // write your code here
        if (time == null || time.length() != 5) {
            return time;
        }
        StringBuilder sb = new StringBuilder(time);
        TreeSet<Integer> set = new TreeSet<Integer>();
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
        
        int lowest = set.first();
        index = 3;
        int[] higherNum = {3, 10, 6, 10};
        Integer cursor;
        for (int i = 4; i >= 0; i--) {
        		if (i == 2) {
        			continue;
        		}
        		cursor = set.higher(timeArr[index]);
        		if (cursor != null && cursor < higherNum[index]) {
        			sb.replace(i, i + 1, "" + cursor);
        			return sb.toString();
        		} else {
        			sb.replace(i, i + 1, "" + lowest);
        		}
        		index--;
        }
        return sb.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NextTime one = new NextTime();
		String[] tests = {"15:59"};
		String[] expecteds = {"23:55", "22:52", "21:22"};
		for (int i = 0, len = tests.length; i < len; i++) {
			Test.assertEquals(expecteds[i], one.nextClosestTime(tests[i]));
		}

	}

}
