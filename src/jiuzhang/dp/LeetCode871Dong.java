package Jiuzhang.DP;

public class LeetCode871Dong {
	
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
    	if (startFuel >= target) {
    		return 0;
    	} else if (stations.length == 0) {
    		return -1;
    	}
    	int len = stations.length + 1;
    	int[][] dp = new int[len][len];
    	for (int i = 0; i < len; i++) {
    		dp[i][0] = startFuel;
    	}
    	for (int j = 1; j < len; j++) {
    		for (int i = j; i < len; i++) {
    			int dis = stations[i - 1][0];
    			int fuel = stations[i - 1][1];
    			if (i > j && dp[i - 1][j] >= dis) {
    				dp[i][j] = dp[i - 1][j];
    			}
    			if (dp[i -1][j -1] >= dis) {
    				dp[i][j] = Math.max(dp[i - 1][j - 1] + fuel, dp[i][j]);
    			}
    			if (dp[i][j] >= target) {
    				return j;
    			}
    		}
    	}
    	return -1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int target = 1000;
//		int startFuel = 83;
//		int[][] stations = {{25,27},{36,187},{140,186},{378,6},{492,202},{517,89},{579,234},{673,86},{808,53},{954,49}};
		
//		int target = 100;
//		int startFuel = 25;
//		int[][] stations = {{25,25},{50,25},{75,25}};
		
		//2
		int target = 100;
		int startFuel = 10;
		int[][] stations = {{10,60},{20,30},{30,30},{60,40}};
		
		System.out.println(minRefuelStops(target, startFuel, stations));
	}

}
