package jiuzhang.dp.matrixpath;

public class LeetCode746MinCostClimbingStairs {
  
  public int minCostClimbingStairs(int[] cost) {
    if (cost == null || cost.length == 0) {
      return 0;
    }
    int len = cost.length + 1;
    int[] dp = new int[len];
    for (int i = 2; i < len; i++) {
      dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    }
    return dp[len - 1];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode746MinCostClimbingStairs one = new LeetCode746MinCostClimbingStairs();
    int[] cost = {1,2,0,0};
    System.out.println(one.minCostClimbingStairs(cost));
  }

}
