package algorithm.leetcode.p901to950;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeetCode904FruitIntoBaskets {
  
  public int totalFruit(int[] tree) {
    int max = 0;
    int type = 0;
    int head = -1;
    int[] count = new int[tree.length];
    for (int i = 0; i < tree.length; i++) {
      if (count[tree[i]]++ == 0) {
        type++;
      }
      
      while (type > 2) {
        if (count[tree[++head]]-- == 1) {
          type--;
        }
      }
      
      if (type <= 2) {
        max = Math.max(max, i - head);
      }
    }
    
    return max;
  }
  
  @Test
  public void testTotalFruit() {
    int[][] trees = {{0,1,2,1},{1,2,3,2,2},{3,3,3,1,2,1,1,2,3,3,4}};
    int[] res = {3,4,5};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], totalFruit(trees[i]));
    }
  }
  
}
