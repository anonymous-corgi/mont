package algorithm.interview.visa;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class SmartSale {
  
  public int deleteProducts(int[] ids, int n, int m) {
    Map<Integer, Integer> count = new HashMap<>(ids.length);
    for (int i = 0; i < ids.length; i++) {
      count.put(ids[i], count.getOrDefault(ids[i], 0) + 1);
    }
    List<Integer> col = new ArrayList<>(count.values());
    Collections.sort(col);
    int types = col.size();
    for (int id : col) {
      if (id > m) {
        break;
      }
      m -= id;
      types--;
    }
    return types;
  }
  
  
  @Test
  public void testDeleteProducts() {
    int[] ids = {1,1,1,2,2,3};
    int n = 4;
    int m = 2;
    int res = 2;
    assertEquals(res, deleteProducts(ids, n, m));
  }

}
