package algorithm.leetcode.p801to850;

public class LeetCode837New21Game {
  
  public double new21Game(int N, int K, int W) {
    if (K == 0 || N >= K + W - 1) {
      return 1;
    }
    double[] dp = new double[N + 1];
    dp[0] = 1;
    double res = 0;
    double prev = 1;
    for (int i = 1; i <= N && i <= K + W; i++) {
      dp[i] = prev / W;
      if (i < K) {
        prev += dp[i];
      } else {
        res += dp[i];
      }
      if (i >= W) {
        prev -= dp[i - W];
      }
    }
    return res;
  }


  public static void main(String[] args) {
//    Expected Result: 0.73278
    int N = 2527;
    int K = 12;
    int W = 8339;
    
////    Expected Result: 0.73278
//    int N = 21;
//    int K = 17;
//    int W = 10;
    
////    Expected Result: 0.60000
//    int N = 6;
//    int K = 1;
//    int W = 10;
    
////    Expected Result: 1.00000
//    int N = 10;
//    int K = 1;
//    int W = 10;
    
////    Expected Result: 1.00000
//    int N = 1;
//    int K = 0;
//    int W = 1;
    
////  Expected Result: 1.00000
//    int N = 0;
//    int K = 0;
//    int W = 1;
    
    LeetCode837New21Game one = new LeetCode837New21Game();
    System.out.println(one.new21Game(N, K, W));
  }

}
