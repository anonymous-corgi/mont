package leetcode.contest;

import java.util.HashSet;
import java.util.Set;

public class LeetCode873LengthOfLongestFibonacciSubsequence {
  
  public int lenLongestFibSubseq(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int len = A.length;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < len; i++) {
      set.add(A[i]);
    }
    
    int max = 0;
    for (int i = 0; i < len - 2; i ++) {
      for (int j = i + 1; j < len - 1; j++) {
        int l = 2;
        int f = A[i];
        int m = A[j];
        int nxt = f + m;
        while (set.contains(nxt)) {
          l++;
          f = m;
          m = nxt;
          nxt = f + m;
        }
        if (l != 2) {
          max = Math.max(max, l);
        }       
      }      
    }    
    return max;
  }
  

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode873LengthOfLongestFibonacciSubsequence one = new LeetCode873LengthOfLongestFibonacciSubsequence();
    int[] arr = {2,4,5,6,7,8,11,13,14,15,21,22,34}; 
    System.out.println(one.lenLongestFibSubseq(arr));
  }

}
