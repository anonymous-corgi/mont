package interview.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import utils.Print;

public class Grouping {
  
  public List<List<Integer>> group(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, LinkedList<Integer>> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      LinkedList<Integer> list = map.getOrDefault(nums[i], new LinkedList<Integer>());
      list.add(i);
      map.put(nums[i], list);
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        LinkedList<Integer> list = map.get(nums[i]);
        List<Integer> subRes = new ArrayList<>();
        for (int j = 0, jLen = nums[i]; j < jLen; j++) {
          int index = list.removeFirst();
          subRes.add(index);
          nums[index] = 0;
        }
        res.add(subRes);
      }
    }
    
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {3,3,3,3,3,1,3};
    Grouping one = new Grouping();
    Print.printListList(one.group(nums));
  }

}
