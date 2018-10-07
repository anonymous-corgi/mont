package basicclass;

public class BinaryIndexedTree {
  
  private final int[] bit;
  private final int[] stub;

  public BinaryIndexedTree(int[] nums) {
    this.stub = new int[nums.length + 1];
    this.bit = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      update(i, nums[i]);
    }
  }
  
  public void update(int i, int val) {
    int index = i + 1;
    int diff = val - stub[index];
    stub[index] = val;
    for (; index < bit.length; index += (index & -index)) {
      bit[index] += diff;
    }
  }

  public int getSum(int i) {
    int res = 0;
    int index = i + 1;
    for (; index > 0; index -= (index & -index)) {
      res += bit[index];
    }
    return res;
  }

  public int getRangeSum(int i, int j) {
    return getSum(j) - getSum(i - 1);
  }

}
