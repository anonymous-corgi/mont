package leetcode.p051to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode090SubsetsII {
  
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, 0, new ArrayList<Integer>(), res);
    return res;
  }
  
  private void dfs(int[] nums,  int pos, List<Integer> sub, List<List<Integer>> res) {
    res.add(sub);
    
    for (int i = pos; i < nums.length; i++) {
      if (i != pos && nums[i] == nums[i - 1]) {
        continue;
      }
      List<Integer> nSub = new ArrayList<Integer>(sub);
      nSub.add(nums[i]);
      dfs(nums, i + 1, nSub, res);
    }
  }

}
