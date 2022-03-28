package algorithm.leetcode.p801to850;

import java.util.Arrays;

public class LeetCode805SplitArrayWithSameAverage {
  
  public boolean check(int[] A, int sum, int count, int start) {       
    if (count == 0) return sum == 0;
    if ((A[start]) > sum / count) return false;
    for (int i = start; i < A.length - count + 1; i ++) {
      if (i > start && A[i] == A[i - 1]) continue;
      if (check(A, sum - A[i], count - 1, i + 1)) return true;
    }
    return false;       
  }
  
  public boolean splitArraySameAverage(int[] A) {
    if (A.length == 1) {
      return false;
    }
    int sum = 0;
    Arrays.sort(A);
    for (int a: A) { sum += a; }
    for (int len = 1; len <= A.length / 2; len ++) {
      if ((sum * len) % A.length == 0) {
        if (check(A, sum * len / A.length, len, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {

  }

}
