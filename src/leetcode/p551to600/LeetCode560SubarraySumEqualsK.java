package leetcode.p551to600;

import java.util.HashMap;
import java.util.Map;

public class LeetCode560SubarraySumEqualsK {
  
  public int subarraySum(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int sum = 0;
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];            
      if (map.containsKey(sum - k)) {
        res += map.get(sum - k);
      }      
      if (map.containsKey(sum)) {
        map.put(sum, map.get(sum) + 1);
      } else {
        map.put(sum, 1);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode560SubarraySumEqualsK one = 
        new LeetCode560SubarraySumEqualsK();
    int[] nums = {-1,-1,1};
    int k = 0;
    System.out.print(one.subarraySum(nums, k));
  }

}
