package leetcode.p801to900;

public class LeetCode849MaximizeDistanceToClosestPerson {
  
//  public int maxDistToClosest(int[] seats) {
//    int res = 0, n = seats.length;
//    for (int i = 0, zero = 0; i < n; ++i) {
//      if (seats[i] == 1) {
//        zero = 0; 
//      } else {
//        res = Math.max(res, (++zero + 1) / 2);
//      }
//    }
//    for (int i = 0, zero = 0; seats[i] == 0; ++i) {
//      res = Math.max(res, ++zero);
//    }
//    for (int i = n - 1, zero = 0; seats[i] == 0; --i) {
//      res = Math.max(res, ++zero);
//    }
//    return res;
//  }
  
  public int maxDistToClosest(int[] seats) {
    if (seats == null || seats.length == 0) {
      return -1;
    }
    int max = 0;
    int[] dp = new int[seats.length];
    int dis = seats.length;
    for (int i = 0; i < seats.length; i++) {
      if (seats[i] == 0) {
        dp[i] = ++dis;
      } else {
        dis = 0;
      }
    }
    dis = seats.length;
    for (int i = seats.length - 1; i >= 0; i--) {
      if (seats[i] == 0) {
        dp[i] = Math.min(dp[i], ++dis);
      } else {
        dis = 0;
      }
      if (dp[i] > max) {
        max = dp[i];
      }
    }
    return max;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode849MaximizeDistanceToClosestPerson one = 
        new LeetCode849MaximizeDistanceToClosestPerson();
    int[] seats = {0,1};
//    int[] seats = {1,0,0,0,1,0,1};
    System.out.println(one.maxDistToClosest(seats));
  }

}
