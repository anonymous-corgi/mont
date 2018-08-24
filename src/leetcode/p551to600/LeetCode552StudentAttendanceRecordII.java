package leetcode.p551to600;

public class LeetCode552StudentAttendanceRecordII {
  
  public static class DP_method {
    
    private static final int MOD = 1000000007;
    
    public int checkRecord(int n) {
      long[] PorL = new long[n + 1]; // ending with P or L, no A
      long[] P = new long[n + 1]; // ending with P, no A
      P[0] = 1;
      P[1] = 1;
      PorL[0] = 1;
      PorL[1] = 2;
      
      for (int i = 2; i <= n; i++) {
        P[i] = PorL[i - 1];
        PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % MOD;
      }
      
      long res = PorL[n];
      for (int i = 0; i < n; i++) { // inserting A into (n-1)-length strings
        res += PorL[i] * PorL[n - i - 1];
        res %= MOD;
      }
      
      return (int) res;
    }
    
  }

  public static void main(String[] args) {

  }

}
