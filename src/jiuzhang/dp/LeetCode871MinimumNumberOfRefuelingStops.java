package jiuzhang.dp;

public class LeetCode871MinimumNumberOfRefuelingStops {	
	
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        } else if (stations.length == 0) {
            return -1;
        }
        int len = stations.length + 1;
        int[][] dp = new int[len][len];       
        for (int i = 0; i < len; i++) {
        	dp[0][i] = startFuel;
        }
        for (int step = 1; step < len; step++) {
        	for (int sIndex = step; sIndex < len; sIndex++) {
        		int dis = stations[sIndex - 1][0];
        		int fuel = stations[sIndex - 1][1];
        		if (sIndex > step) {
        			dp[step][sIndex] = dp[step][sIndex - 1];
        		}
        		if (dp[step - 1][sIndex - 1] >= dis) {
        			dp[step][sIndex] = Math.max(dp[step][sIndex], dp[step - 1][sIndex - 1] + fuel);
        		}
        		if (dp[step][sIndex] >= target) {
        			return step;
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
