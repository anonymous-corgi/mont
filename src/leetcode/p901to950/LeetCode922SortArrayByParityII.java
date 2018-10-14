package leetcode.p901to950;

public class LeetCode922SortArrayByParityII {

  public int[] sortArrayByParityII(int[] A) {
    int even = 0;
    int odd = 1;
    while (even < A.length && odd < A.length) {
      while (even < A.length && A[even] % 2 == 0) {
        even += 2;
      }
      while (odd < A.length && A[odd] % 2 == 1) {
        odd += 2;
      }
      if (even < A.length && odd < A.length) {
        int temp = A[odd];
        A[odd] = A[even];
        A[even] = temp;
      }
    }
    return A;
  }

}
