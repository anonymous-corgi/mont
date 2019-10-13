package algorithm.leetcode.p801to850;

public class LeetCode849MaximizeDistanceToClosestPerson {
  
  public static class DirectSearch_method {
    
    public int maxDistToClosest(int[] seats) {
      if (seats == null || seats.length == 0) {
        return -1;
      }
      int max = 0;
      int l = 0;
      int r = seats.length - 1;
      while (seats[l] == 0) {
        l++;
      }
      while (seats[r] == 0) {
        r--;
      }
      max = Math.max(l, seats.length - 1 - r);
      
      for (int i = l, empty = 0; i <= r; i++) {
        if (seats[i] == 1) {
          empty = 0; 
        } else {
          max = Math.max(max, (++empty + 1) / 2);
        }
      }
      return max;
    }
    
  }
  
  public static class DP_method {
    
    public int maxDistToClosest(int[] seats) {
      if (seats == null || seats.length == 0) {
        return -1;
      }
      int max = 0;
      int dis = seats.length;
      int[] dpL = new int[seats.length];
      int[] dpR = new int[seats.length];
      for (int i = 0; i < seats.length; i++) {
        dpL[i] = seats[i] == 0 ? ++dis : (dis = 0);
      }
      dis = seats.length;
      for (int i = seats.length - 1; i >= 0; i--) {
        dpR[i] = seats[i] == 0 ? ++dis : (dis = 0);
      }
      for (int i = 0; i < seats.length; i++) {
        max = Math.max(max, Math.min(dpL[i], dpR[i]));
      }
      return max;
    }
    
  }
    
  public static class DP_Improved_method {
    
    public int maxDistToClosest(int[] seats) {
      if (seats == null || seats.length == 0) {
        return -1;
      }
      int max = 0;
      int[] dp = new int[seats.length];
      int dis = seats.length;
      for (int i = 0; i < seats.length; i++) {
        dp[i] = seats[i] == 0 ? ++dis : (dis = 0);
      }
      dis = seats.length;
      for (int i = seats.length - 1; i >= 0; i--) {
        dp[i] = seats[i] == 0 ? Math.min(dp[i], ++dis) : (dis = 0);
        max = Math.max(max, dp[i]);
      }
      return max;
    }
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode849MaximizeDistanceToClosestPerson.DirectSearch_method one = 
        new LeetCode849MaximizeDistanceToClosestPerson.DirectSearch_method();
    int[] seats = {0,1};
//    int[] seats = {1,0,0,0,1,0,1};
    System.out.println(one.maxDistToClosest(seats));
  }

}
