package leetcode.p301to350;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode327CountOfRangeSum {
  
//  public int countRangeSum(int[] nums, int lower, int upper) {
//    int n = nums.length;
//    long[] sum = new long[n + 1];
//    long[] aux = new long[n + 1];
//    for (int i = 1; i <= n; i++) {
//        sum[i] = sum[i - 1] + nums[i - 1];
//    }
//    int[] res = new int[1];
//    helper(sum, aux, 0, n, lower, upper, res);
//    return res[0];
//}
//
//private void helper(long[] sum, long[] aux, int lo, int hi, 
//                    int lower, int upper, int[] res) {
//    if (lo >= hi) {
//        return;
//    }
//    int mid = lo + (hi - lo) / 2;
//    helper(sum, aux, lo, mid, lower, upper, res);
//    helper(sum, aux, mid + 1, hi, lower, upper, res);
//    int i = lo, j = lo;
//    for (int k = mid + 1; k <= hi; k++) {
//        while (j <= mid && sum[j] <= sum[k] - lower) {
//            j++;
//        }
//        while (i <= mid && sum[i] < sum[k] - upper) {
//            i++;
//        }
//        res[0] += j - i;
//    }
//    // merge;
//    i = lo;
//    j = mid + 1;
//    int k = lo;
//    while (k <= hi) {
//        if (i > mid) aux[k++] = sum[j++];
//        else if (j > hi) aux[k++] = sum[i++];
//        else if (sum[i] < sum[j]) aux[k++] = sum[i++];
//        else aux[k++] = sum[j++];
//    }
//    for (k = lo; k <= hi; k++) {
//        sum[k] = aux[k];
//    }
//}
  
  class SegmentTreeNode {
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
    } else if (node.left.max < val) {
      update(node.right, val);
    } else {
      update(node.left, val);
      update(node.right, val);
    }
    node.count++;
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

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode327CountOfRangeSum one = new LeetCode327CountOfRangeSum();
    int[] nums = {0,-3,-3,1,1,2};
    int lower = 3;
    int upper = 5;   
//    int[] nums = {-2,5,-1};
//    int lower = -2;
//    int upper = 2;
    System.out.println(one.countRangeSum(nums, lower, upper));

  }

}
