package basicclass;

public class BinaryIndexedTree {
  
  private final int[] stub;
  private final int[] bit;
  public BinaryIndexedTree(int[] nums) {
    this.stub = new int[nums.length + 1];
    this.bit = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      update(i, nums[i]);
    }
  }
  
  public void update(int i, int val) {
    int diff = val - stub[i + 1];
    stub[i + 1] = val;
    for (int j = i + 1; j < bit.length; j += (j & -j)) {
      bit[j] += diff;
    }
  }
  
  public int sumRange(int i, int j) {
    return getSum(j + 1) - getSum(i);
  }
  
  private int getSum(int i) {
    int res = 0;
    for (int j = i; j > 0; j -= (j & -j)) {
      res += bit[j];
    }
    return res;
  }
  
  public static void main(String[] args) {
    int[] nums = {1, 3, 4, 6, 7};
    BinaryIndexedTree one = new BinaryIndexedTree(nums);
    for (int i = 0; i <= nums.length; i++) {
      System.out.println(one.getSum(i));
    }
  }

}
