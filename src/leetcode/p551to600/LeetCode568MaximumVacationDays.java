package leetcode.p551to600;

public class LeetCode568MaximumVacationDays {
  
  public int maxVacationDays(int[][] flights, int[][] days) {
    int k = days[0].length;
    int n = days.length;
    int[][] dpMax = new int[n][k];
    for (int i = 0; i < n; i++) {
      dpMax[i][k - 1] = days[i][k - 1];
    }
    for (int week = k - 2; week >= 0; week--) {
      for (int city = 0; city < n; city++) {
        dpMax[city][week] = days[city][week]
            + getMaxDays(dpMax, city, week + 1, flights);
      }
    }
    return getMaxDays(dpMax, 0, 0, flights);
  }
  public int getMaxDays(int[][] dp, int city, int week, int[][] flights) {
    int max = dp[city][week];
    for (int i = 0; i < flights.length; i++) {
      if (flights[city][i] == 1) {
        max = Math.max(max, dp[i][week]);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
