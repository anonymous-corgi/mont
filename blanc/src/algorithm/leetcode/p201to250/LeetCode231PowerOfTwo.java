package algorithm.leetcode.p201to250;

public class LeetCode231PowerOfTwo {
  
  public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }

}
