package algorithm.leetcode.p651to700;

import java.util.ArrayList;
import java.util.List;

public class LeetCode658FindKClosestElements {
  
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int start = 0;
    int end = arr.length - k;
    List<Integer> res = new ArrayList<>();
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (x - arr[mid] > arr[mid + k] - x) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    for (int i = start, iLen = start + k; i < iLen; i++) {
      res.add(arr[i]);
    }
    return res;
  }

  public static void main(String[] args) {

  }

}
