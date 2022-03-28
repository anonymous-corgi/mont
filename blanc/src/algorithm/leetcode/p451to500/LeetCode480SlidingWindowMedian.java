package algorithm.leetcode.p451to500;

import java.util.TreeSet;

public class LeetCode480SlidingWindowMedian {
  
  public double[] medianSlidingWindow(int[] nums, int k) {
    TreeSet<Integer> small = new TreeSet<>((a, b) -> 
      {return nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);});
    TreeSet<Integer> large = new TreeSet<>((a, b) -> 
      {return nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);});
    double[] res = new double[nums.length - k + 1];
    for (int i = 0; i < nums.length; i++) {
      large.add(i);
      small.add(large.pollFirst());
      if (i >= k) {
        if (!large.remove(i - k)) {
          small.remove((i - k));
        }
      }
      while (small.size() > large.size()) {
        large.add(small.pollLast());
      }
      if (i >= k - 1) {
        res[i - k + 1] = k % 2 == 1 ? nums[large.first()] 
            : (double)((long)nums[large.first()] + (long)nums[small.last()]) / 2;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    LeetCode480SlidingWindowMedian one = 
        new LeetCode480SlidingWindowMedian();
//    int[] nums = {1,3,-1,-3,5,3,6,7};
//    int k = 3;
    
    int[] nums = {-2147483648,-2147483648,2147483647,-2147483648,1,3,-2147483648,-100,8,17,22,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
    int k = 6;
     
//    int[] nums = {2147483647,2147483647};
//    int k = 2;
    one.medianSlidingWindow(nums, k);
  }

}
