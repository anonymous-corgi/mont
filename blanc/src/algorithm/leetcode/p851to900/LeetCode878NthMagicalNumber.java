package algorithm.leetcode.p851to900;

public class LeetCode878NthMagicalNumber {
  
  public int nthMagicalNumber(int N, int A, int B) {
    final long MOD = (long) 1e9 + 7;
    final int LCM = A * B / getGCD(A, B);
    long start = 1;
    long end = (long) 1e14;
    while (start < end) {
      long mid = start + (end - start) / 2;
      if (mid / B + mid / A - mid / LCM >= N) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return (int) (end % MOD);
  }
  
  private int getGCD(int a, int b) {
    return b == 0 ? a : getGCD(b, a % b);
  }

  public static void main(String[] args) {

  }

}
