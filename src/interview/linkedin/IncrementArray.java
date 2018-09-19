package interview.linkedin;

import static org.junit.Assert.assertArrayEquals;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import utils.Print;

public class IncrementArray {
  
  public int[] increment(int[] nums) {
    TreeSet<Integer> tr = new TreeSet<>();
    Set<Integer> has = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (has.add(nums[i])) {
        tr.remove(nums[i]);
        if (!has.contains(nums[i] + 1)) {
          tr.add(nums[i] + 1);
        }
      } else {
        int next = tr.higher(nums[i]);
        has.add(next);
        tr.remove(next);
        if (!has.contains(next + 1)) {
          tr.add(next + 1);
        }
        nums[i] = next;
      }
    }
    return nums;
  }
  
  @Test
  public void testIncrement1() {
    int[] nums = {1,2,2,3,3};
    int[] res = {1,2,3,4,5};
    assertArrayEquals(res, increment(nums));
  }  
  
  @Test
  public void testIncrement2() {
    int[] nums = {1,1,4,2,2};
    int[] res = {1,2,4,3,5};
    assertArrayEquals(res, increment(nums));
  }

  public static void main(String[] args) {
    int[] nums = {1,1,4,2,2};
    IncrementArray one = new IncrementArray();
    Print.printArray(one.increment(nums));
  }

}
