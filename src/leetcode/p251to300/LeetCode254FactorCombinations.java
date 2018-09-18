package leetcode.p251to300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Print;

public class LeetCode254FactorCombinations {
  
  private Map<Integer, List<List<Integer>>> cache;
  
  public List<List<Integer>> getFactors(int n) {
    cache = new HashMap<>();
    return helper(n);
  }
  
  private List<List<Integer>> helper(int n) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }
    List<List<Integer>> res = new ArrayList<>();    
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        List<List<Integer>> sub = getFactors(n / i);
        res.add(Arrays.asList(i, n / i));
        for (List<Integer> list : sub) {
          if (list.get(0) >= i) {
            List<Integer> neo = new ArrayList<>();
            neo.add(i);
            neo.addAll(list);
            res.add(neo);
          }
        }
      }
    }
    cache.put(n, res);
    return res;
  }

  public static void main(String[] args) {
    int n = 32;
    LeetCode254FactorCombinations one = 
        new LeetCode254FactorCombinations();
    Print.printListList(one.getFactors(n));
  }

}