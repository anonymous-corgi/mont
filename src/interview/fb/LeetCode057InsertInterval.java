package interview.fb;

import java.util.ArrayList;
import java.util.List;

import basicclass.Interval;

public class LeetCode057InsertInterval {
	
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        if (newInterval.end < intervals.get(0).start) {
            intervals.add(0, newInterval);
            return intervals;
        }
        if (newInterval.start > intervals.get(intervals.size() - 1).end) {
            intervals.add(newInterval);
            return intervals;
        }
        int pos = binarySearchHelper(intervals, newInterval.start);
        for (int i = 0; i < pos; i++) {
            res.add(intervals.get(i));
        }
        Interval head = newInterval.start < intervals.get(pos).start ? 
            helper(newInterval, intervals.get(pos), intervals) 
            : helper(intervals.get(pos), newInterval, res);
        
        for (int i = pos + 1, len = intervals.size(); i < len; i++) {
            head = helper(head, intervals.get(i), res);
        }
        return res;
    }

    private int binarySearchHelper(List<Interval> intervals, int target) {
        int start = 0;
        int end = intervals.size();
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int pivot = intervals.get(mid).end;
            if (target <= pivot) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (target <= intervals.get(start).end) {
            return start;
        } else {
            return end;
        }
    }

    private Interval helper(Interval a, Interval b, List<Interval> res) {
        if (a.end < b.start) {
            res.add(a);
            return b;
        } else {
            a.end = Math.max(a.end, b.end);
            return a;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
