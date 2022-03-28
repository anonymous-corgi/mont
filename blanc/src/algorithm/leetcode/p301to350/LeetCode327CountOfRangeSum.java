package algorithm.leetcode.p301to350;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LeetCode327CountOfRangeSum {
  
//  Segment Tree method:
  public static class SegmentTree_method {
    
    private static class SegmentTreeNode {
      int count;
      long min;
      long max;
      SegmentTreeNode left;
      SegmentTreeNode right;
      public SegmentTreeNode(long min, long max) {
        this.min = min;
        this.max = max;
      }
    }
    
    private SegmentTreeNode build(Long[] valArr, int low, int high) {
      if(low > high) {
        return null;
      }
      SegmentTreeNode node = new SegmentTreeNode(valArr[low], valArr[high]);
      if(low != high) {
        int mid = (low + high) / 2;
        node.left = build(valArr, low, mid);
        node.right = build(valArr, mid + 1, high);
      }
      return node;
    }
    
    private void update(SegmentTreeNode node, Long val) {
      if(node.max == node.min && node.min == val) {
        node.count++;
        return;
      }
      if (node.right.min > val) {
        update(node.left, val);
      } else {
        update(node.right, val);
      }
      node.count++;
//      node.count = node.left.count + node.right.count;
    }
    
    private int getCount(SegmentTreeNode node, long min, long max) {
      if (min > node.max || max < node.min) {
        return 0;
      }
      if (min <= node.min && max >= node.max) {
        return node.count;
      }
      return getCount(node.left, min, max) + getCount(node.right, min, max);
    }
    
    //This is the most important part of the Segment Tree
    //Because the lower and upper are possibly exceed root node's boundary
    public int countRangeSum(int[] nums, int lower, int upper) {
      if(nums == null || nums.length == 0) {
        return 0;
      }
      int res = 0;
      Set<Long> valSet = new HashSet<Long>();
      long sum = 0;
      for(int i = 0; i < nums.length; i++) {
        sum += (long) nums[i];
        valSet.add(sum);
      }
      
      Long[] valArr = valSet.toArray(new Long[0]);
      Arrays.sort(valArr);
      SegmentTreeNode root = build(valArr, 0, valArr.length - 1);
      
      for(int i = nums.length - 1; i >= 0; i--) {
        update(root, sum);
        sum -= (long) nums[i];
        res += getCount(root, (long) lower + sum, (long) upper + sum);
      }
      return res;
    }
    
  }
  
//  TreeMap method:
  public static class TreeMap_Method {
    
    public int countRangeSum(int[] nums, int lower, int upper) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      TreeMap<Long, Long> map = new TreeMap<Long, Long>();
      map.put((long) 0, (long) 1);
      long sum = 0;
      long count = 0;
      for(int i = 0; i < nums.length; i++){
        sum += nums[i];
        long from = sum - upper;
        long to = sum - lower;
        Map<Long, Long> sub = map.subMap(from, true, to, true);
        for(Long value : sub.values()){
          count += value;
        }
        map.put(sum, map.getOrDefault(sum, 0l) + 1);
      }
      return (int)count;
    }
    
  }

//  Merge Sort method:
  public static class MergeSort_Method {
    
    public int countRangeSum(int[] nums, int lower, int upper) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int n = nums.length;
      long[] sum = new long[n + 1];
      long[] helper = new long[n + 1];
      for (int i = 1; i <= n; i++) {
        sum[i] = sum[i - 1] + nums[i - 1];
      }
      int res = countAndMerge(sum, helper, 0, n, lower, upper);
      return res;
    }
    
    private int countAndMerge(long[] sum, long[] helper, int left, int right, int lower, int upper) {
      if (left >= right) {
        return 0;
      }
      int mid = left + (right - left) / 2;
      int count = countAndMerge(sum, helper, left, mid, lower, upper) + countAndMerge(sum, helper, mid + 1, right, lower, upper);
      for (int i = left; i <= right; i++) {
        helper[i] = sum[i];
      }
      int index = left;
      int l = left;
      int r = mid + 1;
      int start = mid + 1;
      int end = mid + 1;
      while (l <= mid) {
        long cur = helper[l];
        while (start <= right && helper[start] - cur < lower) {
          start++;
        }
        while (end <= right && helper[end] - cur <= upper) {
          end++;
        }
        count += end - start;
        
        while (r <= right && helper[r] < cur) {
          sum[index++] = helper[r++];
        }
        sum[index++] = helper[l++];
      }
      return count;
    }
    
  }

  public static void main(String[] args) {
//    Result = 4
//    int[] nums = {0,-3,-3,1,1,2};
//    int lower = 3;
//    int upper = 5;
//
    int[] nums = {-2,5,-1};
    int lower = -2;
    int upper = 2;
    
//    LeetCode327CountOfRangeSum.TreeMap_Method one =
//        new LeetCode327CountOfRangeSum.TreeMap_Method();
    
    LeetCode327CountOfRangeSum.MergeSort_Method one =
        new LeetCode327CountOfRangeSum.MergeSort_Method();
    System.out.println(one.countRangeSum(nums, lower, upper));
  }

}
