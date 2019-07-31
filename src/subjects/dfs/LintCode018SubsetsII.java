package subjects.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.Print;

public class LintCode018SubsetsII {
  
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    // write your code here
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    int nLen = nums.length;
    if (nLen == 0) {
      return results;
    }
    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<Integer>(), results);
    return results;
  }
  
  private void dfs(int[] nums, int p, List<Integer> aResult, List<List<Integer>> results) {
    results.add(new ArrayList<Integer>(aResult));
    
    for (int i = p; i < nums.length; i++) {
      if (i != p && nums[i] == nums[i - 1]) {
        continue;
      }
      aResult.add(nums[i]);
      dfs(nums, i + 1, aResult, results);
      aResult.remove(aResult.size() - 1);
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LintCode018SubsetsII one = new LintCode018SubsetsII();
    int[] nums = {1, 2};
    Print.printListList(one.subsetsWithDup(nums));

  }

}
