package leetcode.p901to950;

public class LeetCode908SmallestRangeI {
  
  public int smallestRangeI(int[] A, int K) {
    int max = A[0];
    int min = A[0];
    for (int i = 0; i < A.length; i++) {
      max = Math.max(max, A[i]);
      min = Math.min(min, A[i]);
    }
    return max - min <= K * 2 ? 0 : max - min - 2 * K;
  }
  
  public static void main(String[] args) {

  }

}
