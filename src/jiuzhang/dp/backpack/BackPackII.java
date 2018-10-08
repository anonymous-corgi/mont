package jiuzhang.dp.backpack;

//BackPackIII contains repeated object
public class BackPackII {

  //Count Max value can be stored in m capacity WITHOUT repeated use
  public int backPackII(int capacity, int[] weights, int values[]) {
    if (weights == null || weights.length == 0) {
      return 0;
    }
    int[] dp = new int[capacity + 1];
    for (int i = 0; i < weights.length; i++) {
      for (int j = capacity; j >= weights[i]; j--) {
        dp[j] = Math.max(dp[j - 1], dp[j - weights[i]] + values[i]);
      }
    }
    return dp[capacity];
  }
  
}
