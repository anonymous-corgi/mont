package algorithm.leetcode.p201to250;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LeetCode220ContainsDuplicateIII {
  
  public static class Bucket_method {
    
    //If for a and b, theirs' difference within t, we can use this rule:
    //aR = a / (t + 1), bR = b / (t + 1). Math.abs(aR - bR) <= 1;   <=   Math.abs(a - b) <= t;
    //aR = a / (t + 1), bR = b / (t + 1). Math.abs(aR - bR) <= 1;   ��>   Math.abs(a - b) <= t;
    //But, this a necessary condition but not sufficient
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
      if (k < 1 || t < 0) return false;
      Map<Long, Long> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
        long bucket = remappedNum / ((long) t + 1);
        if (map.containsKey(bucket)
            || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
            || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
          return true;
        if (map.entrySet().size() >= k) {
          long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
          map.remove(lastBucket);
        }
        map.put(bucket, remappedNum);
      }
      return false;
    }
    
  }
  
  public static class TreeSet_method {
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
      if (nums == null || nums.length == 0 || k == 0) {
        return false;
      }
      TreeSet<Long> set = new TreeSet<>();
      for (int i = 0; i < nums.length; i++) {
        if (isWR(set, (long) nums[i], t)) {
          return true;
        }
        set.add((long)nums[i]);
        if (i >= k) {
          set.remove((long)nums[i - k]);
        }
      }
      return false;
    }
    
    //Within Range
    private boolean isWR(TreeSet<Long> set, long num, int t) {
      Long cursor = set.ceiling(num);
      if (cursor != null && cursor - num <= t) {
        return true;
      }
      cursor = set.floor(num);
      if (cursor != null && num - cursor <= t) {
        return true;
      }
      return false;
    }
    
  }

  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
