package leetcode.p501to550;

public class LeetCode526BeautifulArrangement {
  
  private int count;
  
  public int countArrangement(int N) {
    if (N == 0) return 0;
    int[] nums = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      nums[i] = i;
    }
    helper(nums, N);
    return count;
  }
  
  private void helper(int[] nums, int start) {
    if (start == 0) {
      count++;
      return;
    }
    for (int i = start; i > 0; i--) {
      swap(nums, start, i);
      if (nums[start] % start == 0 || start % nums[start] == 0) {
        helper(nums, start - 1);
      }
      swap(nums, i, start);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
