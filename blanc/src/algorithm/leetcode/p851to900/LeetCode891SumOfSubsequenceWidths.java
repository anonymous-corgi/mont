package algorithm.leetcode.p851to900;

import java.util.Arrays;

public class LeetCode891SumOfSubsequenceWidths {
  
  private long sum_max = 0l;
  private long sum_min = 0l;
  private final long MOD = 1000000007L;
  private long[] power_mod;
  
  public int sumSubseqWidths(int[] A) {
    Arrays.sort(A);
    power_mod = new long[A.length];
    long base = 1;
    for(int i = 0; i < A.length; i++) {
        power_mod[i] = base;
        base <<= 1;
        base %= MOD;
    }
    
    for (int i = 0; i < A.length; i++) {
      long min_choice = power_mod[A.length - 1 - i];
      long max_choice = power_mod[i];
      sum_max += max_choice * (long) (A[i]);
      sum_max %= MOD;
      sum_min += min_choice * (long) (A[i]);
      sum_min %= MOD;
    }
    if(sum_max < sum_min) {
      sum_max += MOD;
    }
    return (int) (sum_max - sum_min);
  }

  public static void main(String[] args) {

  }

}
