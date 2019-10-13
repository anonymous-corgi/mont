package algorithm.interview.linkedin;

import java.util.ArrayList;
import java.util.List;
import algorithm.base.utils.Print;

public class ParityPermutation {
  
  public List<List<Integer>> getPermutation(int n) {
    List<List<Integer>> res = new ArrayList<>();
    if (n < 1) {
      return res;
    }
    boolean[] used = new boolean[n + 1];
    List<Integer> sub = new ArrayList<>();
    helper(used, 1, true, sub, res);
    return res;
  }
  
  private void helper(boolean[] used, int pos, boolean isOdd, List<Integer> sub, List<List<Integer>> res) {
    if (pos == used.length) {
      res.add(new ArrayList<Integer>(sub));
      return;
    }
    for (int i = 1; i < used.length; i++) {
      if (used[i] || (pos != 1 && ((i % 2 == 1) != isOdd))) {
        continue;
      }
      sub.add(i);
      used[i] = true;
      helper(used, pos + 1, i % 2 != 1, sub, res);
      sub.remove(sub.size() - 1);
      used[i] = false;
    }
  }

  public static void main(String[] args) {
    int n = 5;
    ParityPermutation one =
        new ParityPermutation();
    Print.printListList(one.getPermutation(n));
  }

}
