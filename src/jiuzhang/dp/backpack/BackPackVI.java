package Jiuzhang.DP.BackPack;

/**
 * LintCode 564. Combination Sum IV
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
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 * [4]
 * return 6
 */
public class BackPackVI {
	//Count How many ways can get to target with repeated use
    public int backPackVI(int[] nums, int target) {
        // write your code here
    		if (nums == null) {
    			return 0;
    		}
    		int nLen = nums.length;
    		if (nLen == 0) {
    			return 0;
    		}
    		int[] dp = new int[target + 1];
    		dp[0] = 1;
    		for (int i = 1; i <= target; i++) {
    			//j starts from 0, because it contains duplication.
    			for (int j = 0; j < nLen; j++) {
    				if (nums[j] > i) {
    					continue;
    				}
    				dp[i] += dp[i - nums[j]];
    			}
    		}
    		return dp[target];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
