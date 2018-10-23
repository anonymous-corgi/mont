package interview.google;

import java.util.HashSet;
import java.util.Set;

public class PerfectSquareII {
  
  public boolean find(int n) {
    if (n < 1) {
      return false;
    }
    Set<Integer> set = new HashSet<>();
    for (int i = 1, s = 1; s < n; s = ++i * i) {
      set.add(s);
    }
    for (Integer each : set) {
      if (set.contains(n - each)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    PerfectSquareII one = new PerfectSquareII();
    int n = 18;
    System.out.println(one.find(n));
  }

}
