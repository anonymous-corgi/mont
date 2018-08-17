package jiuzhang.dp;

public class LeetCode152MaximumProductSubarray {
  
  public int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int nLen = nums.length;
    int max = nums[0];
    int min = nums[0];
    int result = nums[0];
    for (int i = 1; i < nLen; i++) {
      if (nums[i] > 0) {
        max = Math.max(nums[i], max * nums[i]);
        min = Math.min(nums[i], min * nums[i]);
      } else if (nums[i] < 0) {
        int preMax = max;
        max = Math.max(nums[i], min * nums[i]);
        min = Math.min(nums[i], preMax * nums[i]);
      } else {
        max = 0;
        min = 0;
      }
      result = Math.max(result, max);
    }
    return result;
  }

  public static void main(String[] args) {
    LeetCode152MaximumProductSubarray one
    = new LeetCode152MaximumProductSubarray();
    int[] nums = {-4,-3,-2};
    System.out.println(one.maxProduct(nums));
  }

}
