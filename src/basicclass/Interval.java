package basicclass;

import com.sun.istack.internal.Nullable;

public class Interval {
  
  public int start;
  public int end;
  
  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

	@Nullable
	public static Interval deserialize(String data) {
		if (data == null || data.length() < 6) {
			System.out.println("<Error> The input data is invalid.");
			return null;
		}
		String[] nums = data.substring(1, data.length() - 1).split(",");
		if (nums.length != 2) {
			System.out.println("<Error> The input data is invalid.");
			return null;
		}
		int a = Integer.parseInt(nums[0]);
		int b = Integer.parseInt(nums[1]);
		if (a > b) {
			System.out.println("<Error> The input data is invalid: start > end.");
			return null;
		}
		return new Interval(a, b);
	}
  
}
