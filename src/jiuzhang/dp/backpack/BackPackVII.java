package jiuzhang.dp.backpack;

/**
 * 
 * Description
 * Given an integer array nums with all positive numbers and no duplicates, 
 * find the number of possible combinations that add up to a positive integer target.
 * 
 * Example
 * Given nums = [1, 2, 4], target = 4
 * The possible combination ways are:
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [2, 2]
 * [4]
 * return 4
 */
public class BackPackVII {
	
	public int backPackVII(int[] nums, int target) {
		// write your code here
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int nLen = nums.length;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 0; i < nLen; i++) {
			for (int j = nums[i]; j <= target; j++) {
				dp[j] += dp[j - nums[i]];
			}
		}
		return dp[target];
	}
	
	/**
	 * 
	 * The solution will return combination.
	 * Because the n-1 situation in here is the result made by the first n-1 elements,
	 * the previous n-1 results don't contain the nth element.
	 * so the result is kind of combination rather than permutation.  
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
