package leetcode.p001to100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class M56MergeIntervals {
	
	  public static class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	  }
	 
	
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        rainbowSort(intervals, 0, intervals.size() - 1);
        intervals.sort(new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        int neoCursor = 0;
        List<Interval> neoList = new ArrayList<Interval>();
        neoList.add(intervals.get(0));
        for (int preCursor = 1; preCursor < intervals.size(); preCursor++) {
            Interval neo = neoList.get(neoCursor);
            Interval pre = intervals.get(preCursor);
            if (neo.end < pre.start) {
                neoList.add(pre);
                neoCursor++;
            } else {
                neoList.set(neoCursor, mergeTwoIntervals(neo, pre));
            }
        }
        return neoList;
    }
    
    private Interval mergeTwoIntervals(Interval a, Interval b) {
        if (a.end < b.end) {
            a.end = b.end;
        }
        return a;
    }
    
    private void rainbowSort(List<Interval> intervals, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start - 1;
        int right = end + 1;
        int cursor = start;
        int pivot = intervals.get(start + (end - start) / 2).start;
        while (start < right) {
            int cursorNum = intervals.get(cursor).start;
            if (cursorNum < pivot) {
                left++;
                swap(intervals, cursor, left);
                cursor++;
            } else if (cursorNum > pivot) {
                right--;
                swap(intervals, cursor, right);
            } else {
                cursor++;
            }
        }
        rainbowSort(intervals, start, left);
        rainbowSort(intervals, right, end);
    }
    
    private void swap(List<Interval> intervals, int a, int b) {
        Interval temp = intervals.get(a);
        intervals.set(a, intervals.get(b));
        intervals.set(b, temp);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arrs = {{1,3},{2,6},{8,10},{15,18}};
		List<Interval> list = new ArrayList<>();
		for (int i = 0; i < arrs.length; i++) {
			list.add(new Interval(arrs[i][0], arrs[i][1]));
		}
		M56MergeIntervals one = new M56MergeIntervals();
		one.merge(list);
	}

}
