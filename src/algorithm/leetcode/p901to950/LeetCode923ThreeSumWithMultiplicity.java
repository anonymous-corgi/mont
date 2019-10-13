package algorithm.leetcode.p901to950;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode923ThreeSumWithMultiplicity {

  private final long MOD = (int) 1e9 + 7;

  public int threeSumMulti(int[] A, int target) {
    Map<Integer, Long> freq = new HashMap<>(A.length);
    for (int n : A) {
      freq.put(n, freq.getOrDefault(n, 0l) + 1);
    }
    List<Integer> list = new ArrayList<>(freq.keySet());
    Collections.sort(list);
    long res = 0;
    for (int k = list.size() - 1; k >= 0; k--) {
      int i = 0;
      int j = k;
      int subTarget = target - list.get(k);
      while (i <= j) {
        int sum = list.get(i) + list.get(j);
        if (sum > subTarget) {
          j--;
        } else if (sum < subTarget) {
          i++;
        } else {
          long iF = freq.get(list.get(i));
          long jF = freq.get(list.get(j));
          long kF = freq.get(list.get(k));
          if (i == j && j == k) {
              res += (kF * (jF - 1) * (iF - 2) / 6) % MOD;
          } else if (i == j || j == k) {
              res += (kF * (jF - 1) * iF / 2) % MOD;
          } else {
              res += (kF * jF * iF) % MOD;
          }
          res %= MOD;
          j--;
          i++;
        }
      }
    }
    return (int) res;
  }

}
