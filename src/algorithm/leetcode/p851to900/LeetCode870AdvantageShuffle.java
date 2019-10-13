package algorithm.leetcode.p851to900;

import java.util.Arrays;

public class LeetCode870AdvantageShuffle {
  
  public int[] advantageCount(int[] A, int[] B) {
    int[] res = new int[B.length];
    int[][] bMap = new int[B.length][2];
    for (int i = 0; i < B.length; i++) {
      bMap[i][0] = B[i];
      bMap[i][1] = i;
    }
    Arrays.sort(A);
    Arrays.sort(bMap, (a, b) -> (a[0] - b[0]));
    int left = 0;
    int right = bMap.length - 1;
    int bIndex = bMap.length - 1;
    while (left <= right) {
      if (A[right] > bMap[bIndex][0]) {
        res[bMap[bIndex--][1]] = A[right--];
      } else {
        res[bMap[bIndex--][1]] = A[left++];
      }
    }
    return res;
  }

  public static void main(String[] args) {

  }

}
