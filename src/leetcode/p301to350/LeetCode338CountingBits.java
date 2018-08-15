package leetcode.p301to350;

public class LeetCode338CountingBits {
  
  public static class MoveBit_method {
    
    public int[] countBits(int num) {
      int[] dp = new int[num + 1];
      for (int i = 0; i <= num; i++) {
        dp[i] = dp[i >> 1] + (i & 1); 
      }
      return dp;
    }
    
  }
  
  public static class BitOP_method {
    
    public int[] countBits(int num) {
      int[] dp = new int[num + 1];
      for (int i = 1; i <= num; i++) {
        dp[i] = dp[i & (i - 1)] + 1;
      }
      return dp;
    }
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode338CountingBits.BitOP_method one = 
        new LeetCode338CountingBits.BitOP_method();
    int num = 10;
    System.out.println(one.countBits(num));
  }

}
