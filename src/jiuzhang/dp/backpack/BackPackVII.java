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
	
	public int backPackVII(int[] weights, int capacity) {
		// write your code here
		if (weights == null || weights.length == 0) {
			return 0;
		}
		int[] dp = new int[capacity + 1];
		dp[0] = 1;
		for (int i = 0; i < weights.length; i++) {
			for (int j = weights[i]; j <= capacity; j++) {
				dp[j] += dp[j - weights[i]];
			}
		}
		return dp[capacity];
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
		
	}

}
