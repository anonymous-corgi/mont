package leetcode.p551to600;

public class LeetCode568MaximumVacationDays {
  
  public int maxVacationDays(int[][] flights, int[][] days) {
    int weeks = days[0].length;
    int citys = days.length;
    int[] preDp = new int[citys];
    int[] curDp = new int[citys];
    for (int city = 0; city < citys; city++) {
      curDp[city] = days[city][weeks - 1];
    }
    for (int week = weeks - 2; week >= 0; week--) {
      int[] temp = preDp;
      preDp = curDp;
      curDp = temp;
      for (int city = 0; city < citys; city++) {
        curDp[city] = days[city][week]
            + getMaxDays(preDp, city, flights);
      }
    }
    return getMaxDays(curDp, 0, flights);
  }
  
  public int getMaxDays(int[]dp, int city, int[][] flights) {
    int max = dp[city];
    for (int i = 0; i < flights.length; i++) {
      if (flights[city][i] == 1) {
        max = Math.max(max, dp[i]);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
