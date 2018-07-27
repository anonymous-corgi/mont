package jiuzhang.dp.longestincreasingsubsequence;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode354RussianDollEnvelopes {
	
    private int max; 
    
    public int maxEnvelopes(int[][] envelopes) {
        // write your code here
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        if (envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> 
                        {return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];});
        int eLen = envelopes.length;
        max = 0;
        int[] dp = new int[eLen + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < eLen; i++) {
            findP(dp, envelopes[i][1]);
        }
        return max;
    }
    
    private void findP(int[] dp, int target) {
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
    
    public int maxEnvelopes2(int[][] envelopes) {
      // Write your code here
      if(envelopes == null || envelopes.length == 0 
          || envelopes[0] == null || envelopes[0].length != 2)
          return 0;
      
      Arrays.sort(envelopes, new Comparator<int[]>(){
          public int compare(int[] arr1, int[] arr2){
              if(arr1[0] == arr2[0])
                  return arr2[1] - arr1[1];
              else
                  return arr1[0] - arr2[0];
          } 
      });
      
      int dp[] = new int[envelopes.length];
      int len = 0;
      for(int[] envelope : envelopes){
          int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
              if(index < 0)
                  index = -index - 1;
          dp[index] = envelope[1];
          if (index == len)
              len++;
      }
      return len;
  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
