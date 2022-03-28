package algorithm.interview.visa;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class BudgetShopping {
  
  public int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
    int[] f = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 0, jLen = bundleQuantities.size(); j < jLen; j++) {
        if (bundleCosts.get(j) <= i) {
          f[i] = Math.max(f[i], f[i - bundleCosts.get(j)] + bundleQuantities.get(j));
        }
      }
    }
    return f[n];
  }
  
  
  @Test
  public void testBudgetShopping() {
    int n = 58;
    List<Integer> bundleQuantities = Arrays.asList(20, 19);
    List<Integer> bundleCosts = Arrays.asList(24, 20);
    int res = 40;
    assertEquals(res, budgetShopping(n, bundleQuantities, bundleCosts));
  }

  public static void main(String[] args) {

  }

}
