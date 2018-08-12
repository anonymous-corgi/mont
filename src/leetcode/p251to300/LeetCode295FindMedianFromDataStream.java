package leetcode.p251to300;

import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode295FindMedianFromDataStream {
  
  private Queue<Long> small = new PriorityQueue<>((a, b) -> (int)(b - a));
  private Queue<Long> large = new PriorityQueue<>();
  
  public void addNum(int num) {
    large.add((long) num);
    small.add(large.poll());
    if (large.size() < small.size())
      large.add(small.poll());
  }
  
  public double findMedian() {
    return large.size() != small.size()
        ? large.peek()
            : (large.peek() + small.peek()) / 2.0;
  }

  public static void main(String[] args) {
    LeetCode295FindMedianFromDataStream one = 
        new LeetCode295FindMedianFromDataStream();
    int[] nums = {1,2,3,4,5};
    for (int i = 0; i < nums.length; i++) {
      one.addNum(nums[i]);
      System.out.println(one.findMedian());
    }
  }

}
