package leetcode.p301to350;

public class LeetCode307RangeSumQueryMutable {
  
  public static class BinaryIndexedTree_Method {
    
    private final int[] bit;
    private final int[] nums;
    public BinaryIndexedTree_Method(int[] nums) {
//    public NumArray(int[] nums) {
      this.bit = new int[nums.length + 1];
      this.nums = new int[nums.length + 1];
      for (int i = 0; i < nums.length; i++) {
        update(i, nums[i]);
      }
    }
    
    public void update(int i, int val) {
      int diff = val - nums[++i];
      nums[i] = val;
      for (; i < bit.length; i += (i & -i)) {
        bit[i] += diff;
      }
    }
    
    public int sumRange(int i, int j) {
      return getSum(j + 1) - getSum(i);
    }
    
    private int getSum(int i) {
      int res = 0;
      for (; i > 0; i -= (i & -i)) {
        res += bit[i];
      }
      return res;
    }
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
