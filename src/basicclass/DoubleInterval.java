package basicclass;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;

public class DoubleInterval {
	public double start;
	public double end;
	
	public DoubleInterval(double start, double end) {
		this.start = start;
		this.end = end;
	}
	
	@Nullable
	public static DoubleInterval deserialize(String data) {
		if (data == null || data.length() < 6) {
			System.out.println("<Error> The input data is invalid.");
			return null;
		}
		String[] nums = data.substring(1, data.length() - 1).split(",");
		if (nums.length != 2) {
			System.out.println("<Error> The input data is invalid.");
			return null;
		}
		double a = Double.parseDouble(nums[0]);
		double b = Double.parseDouble(nums[1]);
		if (a > b) {
			System.out.println("<Error> The input data is invalid: start > end.");
			return null;
		}
		return new DoubleInterval(a, b);
	}
	
	public static List<DoubleInterval> deserializeToList(String data) {
		ArrayList<DoubleInterval> res = new ArrayList<>();
		if (data == null || data.length() < 8) {
			System.out.println("<Error> The input data is invalid.");
			return res;
		}
		String[] intervals = data.substring(1, data.length() - 1).split(",");
		for (int i = 0, len = intervals.length; i < len; i++) {
			DoubleInterval cursor = deserialize(intervals[i]);
			if (cursor != null) {
				res.add(cursor);
			} 
		}
		return res;
	}
}
