package leetcode.p601to650;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode632SmallestRange {
  
  public int[] smallestRange(List<List<Integer>> nums) {
    int K = nums.size();
    int[] stub = new int[K];
    List<int[]> list = new ArrayList<>();
    for (int k = 0; k < K; k++) {
      List<Integer> cur = nums.get(k);
      for (int i = 0, iLen = cur.size(); i < iLen; i++) {
        list.add(new int[]{cur.get(i), k});
      }
    }
    Collections.sort(list, (a, b) -> (a[0] - b[0]));
    
    int minGap = Integer.MAX_VALUE;
    int minStart = 0;
    int minEnd = 0;
    int head = 0;
    int groups = 0;
    for (int i = 0, iLen = list.size(); i < iLen; i++) {
      int[] cur = list.get(i);
      if (stub[cur[1]]++ == 0) {
        groups++;
      }
      while (groups == K) {
        int[] h = list.get(head);
        int[] e = list.get(i);
        if (e[0] - h[0] < minGap) {
          minStart = h[0];
          minEnd = e[0];
          minGap = minEnd - minStart;
        }
        if (stub[h[1]]-- == 1) {
          groups--;
        }
        head++;
      }
    }
    return new int[]{minStart, minEnd};
  }

  public static void main(String[] args) {

  }

}
