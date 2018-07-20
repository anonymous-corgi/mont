package interview.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LintCode057ThreeSum {

  public List<List<Integer>> threeSum(int[] nums) {
    // write your code here
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    Arrays.sort(nums);
    for (int i = 0, len = nums.length; i < len - 2; ) {
      int target = -nums[i];
      int left = i + 1;
      int right = len - 1;
      while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum < target) {
          do {
            left++;
          } while (left < right && nums[left] == nums[left - 1]);
        } else if (sum > target) {
          do {
            right--;
          } while (left < right && nums[right] == nums[right + 1]);
        } else {
          List<Integer> res = new ArrayList<>();
          res.add(nums[i]);
          res.add(nums[left]);
          res.add(nums[right]);
          results.add(res);
          do {
            left++;
          } while (left < right && nums[left] == nums[left - 1]);
          do {
            right--;
          } while (left < right && nums[right] == nums[right + 1]);
        }
      }
      do {i++;} 
      while (i < len && nums[i] == nums[i - 1]);
    }
    return results;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
