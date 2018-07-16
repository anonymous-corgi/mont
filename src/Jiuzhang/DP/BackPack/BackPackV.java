package Jiuzhang.DP.BackPack;

public class BackPackV {
	
	public int backPackV(int[] nums, int target) {
		if (nums == null) {
			return 0;
		}
		int len = nums.length;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 0; i < len; i++) {
			//If j starts from nums[i] might cause duplication 
			for (int j = target; j >= nums[i]; j--) {
				dp[j] += dp[j - nums[i]];
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
