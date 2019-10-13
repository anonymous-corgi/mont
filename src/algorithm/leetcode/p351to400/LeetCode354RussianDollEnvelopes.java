package algorithm.leetcode.p351to400;

import java.util.Arrays;

public class LeetCode354RussianDollEnvelopes {
  
  public static class Binary_method {
    
    public int maxEnvelopes(int[][] envelopes) {
      if (envelopes == null || envelopes.length == 0
          || envelopes[0] == null || envelopes[0].length != 2) {
        return 0;
      }
      
      Arrays.sort(envelopes, (a, b) -> 
            { return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]; });
      
      int len = 0;
      int dp[] = new int[envelopes.length];
      for(int[] envelope : envelopes){
        int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
        if (index < 0) {
          index = -index - 1;
        }
        dp[index] = envelope[1];
        if (index == len) {
          len++;
        }
      }
      return len;
    }
    
  }
  
  
  public static class CustomBinary_method {
    
    private int max; 
    
    public int maxEnvelopes(int[][] envelopes) {
      if (envelopes == null || envelopes.length == 0
          || envelopes[0] == null || envelopes[0].length != 2) {
        return 0;
      }
      
      Arrays.sort(envelopes, (a, b) -> 
            { return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]; });
      
      int eLen = envelopes.length;
      max = 0;
      int[] dp = new int[eLen + 1];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      
      for (int i = 0; i < eLen; i++) {
        binarySearch(dp, envelopes[i][1]);
      }
      return max;
    }
    
    private void binarySearch(int[] dp, int target) {
      int start = 0;
      int end = max + 1;
      while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        int pivot = dp[mid];
        if (pivot >=  target) {
          end = mid;
        } else {
          start = mid;
        }
      }
      dp[end] = target;
      if (end > max) {
        max = end;
      }
    }
    
  }
  
  public static void main(String[] args) {
    
  }
  
}
