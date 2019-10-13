package algorithm.leetcode.p101to150;

import java.util.ArrayList;
import java.util.List;

public class LeetCode119PascalsTriangleII {

  public List<Integer> getRow(int rowIndex) {
    List<Integer> res = new ArrayList<>(rowIndex + 1);
    res.add(1);
    for (int i = 0; i < rowIndex; i++) {
      res.add(1);
      for (int j = i; j > 0; j--) {
        res.set(j, res.get(j) + res.get(j - 1));
      }
    }
    return res;
  }

}
