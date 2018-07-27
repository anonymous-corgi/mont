package interview.google.phoneinterview;

public class LeetCode135Candy {
  
  public int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) {
      return 0;
    }
    int res = 0;
    int len = ratings.length;
    int[] dp = new int[len];
    dp[0] = 1;
    for (int i = 1; i < len; i++) {
      dp[i] = ratings[i] > ratings[i - 1] ? dp[i - 1] + 1 : 1;
    }
    for (int i = len - 1; i > 0; i--) {
      if (ratings[i - 1] > ratings[i]) {
        dp[i - 1] = Math.max(dp[i] + 1, dp[i - 1]);
      }
    }
    for (int i = 0; i < len; i++) {
      res += dp[i];
    }
    return res;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode135Candy one = new LeetCode135Candy();
    int[] ratings = {1, 0, 2};
    System.out.println(one.candy(ratings));
    
  }

}
